package com.windriver.core;

import com.windriver.model.automation.*;
import com.windriver.model.request.ActionControlRequest;
import com.windriver.model.request.ApplicationControlRequest;
import com.windriver.model.request.ElementLocationControlRequest;
import com.windriver.model.response.ActionControlResponse;
import com.windriver.model.response.ApplicationControlResponse;
import com.windriver.model.response.AvailableNodesResponse;
import com.windriver.model.response.ElementLocationControlResponse;
import com.windriver.util.CmdExecutor;
import com.windriver.util.PortFinder;
import com.windriver.util.PropertyConditions;
import com.windriver.util.URLBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ConnectException;
import java.util.*;

public class WinDriverClient {

    private static final Logger log = LoggerFactory.getLogger(WinDriverClient.class);
    private RestTemplate restTemplate;
    private URLBuilder urlBuilder;
    private String sessionId;
    private CmdExecutor cmdExecutor;

    public WinDriverClient() {
        cmdExecutor = new CmdExecutor();
        urlBuilder = new URLBuilder();
        restTemplate = new RestTemplate(ConfiguredRequestFactory.defaultFactory());
        restTemplate.setErrorHandler(new ErrorHandler());
        setLogLevel(Level.toLevel(EnvironmentProperties.CLIENT_LOG_LEVEL.readString()));
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public URLBuilder getUrlBuilder() {
        return urlBuilder;
    }

    public String getSessionId() {
        return sessionId;
    }

    public CmdExecutor getCmdExecutor() {
        return cmdExecutor;
    }

    private String createAgentUrl() {
        return EnvironmentProperties.AGENT_URL_CONNECTION_TYPE.readString() +
                "://" + EnvironmentProperties.AGENT_URL_HOST.readString() +
                ":" + PortFinder.findFreePort();
    }

    private String createHubUrl() {
        return EnvironmentProperties.HUB_URL_CONNECTION_TYPE.readString() +
                "://" + EnvironmentProperties.HUB_URL_HOST.readString() +
                ":" + EnvironmentProperties.HUB_URL_PORT.readString();
    }

    public void connectToAgent(String agentUrl) throws ConnectException {
        Optional<Map.Entry<String, String>> availableAgentNode = getAvailableNodes().entrySet().stream()
                .filter(entry -> entry.getValue().equals(agentUrl)).findFirst();

        if (availableAgentNode.isPresent()) {
            sessionId = availableAgentNode.get().getKey();
            restTemplate.setInterceptors(Collections.singletonList(new SessionIdHeaderInterceptor(sessionId)));
            log.info("Connection with agent [" + agentUrl + "] created successfully. SessionId = " + sessionId);
        } else {
            throw new ConnectException("Can't create connection with agent [" + agentUrl + "]. Agent is not available");
        }
    }

    public WinDriverApplication launch(StartMethodNameOption methodName, String executable, String processName) {
        log.info("Launching app with arguments [" + methodName + ", " + executable + ", " + processName + "]");
        String url = urlBuilder.addHubHeadURL().addSubItem("WinAuto").addSubItem("Applications").buildURL();
        ApplicationControlRequest request = new ApplicationControlRequest(methodName, executable, processName);
        ApplicationControlResponse response = restTemplate.postForObject(url, request, ApplicationControlResponse.class);
        return response != null ? response.getWinDriverApplication() : null;
    }

    public void launchAgent(String pathToAgentExecutable, String agentUrl, String hubUrl) throws IOException, InterruptedException {
        cmdExecutor.execute(pathToAgentExecutable, "url=" + agentUrl, "hub=" + hubUrl);
        connectToAgent(agentUrl);
    }

    public void launchAgent(String pathToAgentExecutable) throws IOException, InterruptedException {
        String agentUrl = createAgentUrl();
        String hubUrl = createHubUrl();
        cmdExecutor.execute(pathToAgentExecutable, "url=" + agentUrl, "hub=" + hubUrl);
        connectToAgent(agentUrl);
    }

    public void closeAgent() throws IOException {
        cmdExecutor.closeProcess();
    }

    public void shutdown(String id) {
        log.info("Closing app with windriverId '" + id + "'");
        String url = urlBuilder.addHubHeadURL().addSubItem("WinAuto").addSubItem("Applications").addSubItem(id).buildURL();
        restTemplate.delete(url, String.class);
    }

    public ElementLocationControlResponse find(ElementLocationControlRequest request) {
        log.info("Trying to find element using request: " + request);
        String url = urlBuilder.addHubHeadURL().addSubItem("WinAuto").addSubItem("AutomationElementLocations").buildURL();
        ElementLocationControlResponse response = restTemplate.postForObject(url, request, ElementLocationControlResponse.class);
        response.getWinDriverElements().forEach(element -> element.setWinDriverClient(this));
        return response;
    }

    public ActionControlResponse interact(ActionControlRequest request) {
        log.info("Performing action using request: " + request);
        String url = urlBuilder.addHubHeadURL().addSubItem("WinAuto").addSubItem("AutomationElements").buildURL();
        return restTemplate.postForObject(url, request, ActionControlResponse.class);
    }

    public HashMap<String, String> getAvailableNodes() {
        String url = urlBuilder.addHubHeadURL().addSubItem("nodes").buildURL();
        return restTemplate.getForObject(url, AvailableNodesResponse.class).getAvailableNodes();
    }

    private ElementLocationControlResponse findWindowsByCondition(PropertyConditions conditions) {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(SearchScopeOption.CHILDREN);
        request.setParentWinDriverElementId("");
        request.setFindOption(FindOption.FIND_ALL);
        request.setConditionModels(conditions.getConditions());
        return find(request);
    }

    public WinDriverElement getWindow(PropertyConditions conditions) {
        ElementLocationControlResponse response = findWindowsByCondition(conditions);
        if (response != null && !response.getWinDriverElements().isEmpty())
            return response.getWinDriverElements().get(0);
        return null;
    }

    public List<WinDriverElement> getWindows(PropertyConditions conditions) {
        ElementLocationControlResponse response = findWindowsByCondition(conditions);
        return response != null && response.getWinDriverElements() != null ? response.getWinDriverElements() : null;
    }

    public List<WinDriverElement> getAllWindows() {
        PropertyConditions conditions = new PropertyConditions(Property.TRUE_CONDITION, "");
        ElementLocationControlResponse response = findWindowsByCondition(conditions);
        return response != null && response.getWinDriverElements() != null ? response.getWinDriverElements() : null;
    }

    public ResponseEntity<String> healthCheck() {
        log.info("Performing health check request");
        String url = urlBuilder.addHubHeadURL().addSubItem("WinAuto").addSubItem("HealthCheck").buildURL();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public void setLogLevel(Level logLevel) {
        log.info("Changing log level to '" + logLevel + "'");
        LogManager.getRootLogger().setLevel(logLevel);
    }
}
