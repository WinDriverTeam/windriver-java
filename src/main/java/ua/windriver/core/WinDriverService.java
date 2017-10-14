package ua.windriver.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.windriver.model.automation.*;
import ua.windriver.model.exception.WinDriverElementNotFoundException;
import ua.windriver.model.request.*;
import ua.windriver.model.response.ActionControlResponse;
import ua.windriver.model.response.ApplicationControlResponse;
import ua.windriver.model.response.ElementLocationControlResponse;
import ua.windriver.util.PropertyConditions;
import ua.windriver.util.URLBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WinDriverService {

    private static final Logger log = LoggerFactory.getLogger(WinDriverService.class);
    private RestTemplate restTemplate;
    private URLBuilder urlBuilder;
    private ObjectMapper objMapper;
    private Class<? extends WinDriverElement> winDriverElementType;

    public WinDriverService() {
        urlBuilder = new URLBuilder();
        objMapper = new ObjectMapper();
        winDriverElementType = WinDriverElement.class;
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

    public Class<? extends WinDriverElement> getWinDriverElementType() {
        return winDriverElementType;
    }

    public void setWinDriverElementType(Class<? extends WinDriverElement> winDriverElementType) {
        this.winDriverElementType = winDriverElementType;
    }

    private String convertObjectToString(Object obj) {
        try {
            return objMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Cannot convert value");
        }
        return null;
    }

    public WinDriverApplication launchByExecutable(String executable, String processName) {
        log.info("Launch app by executable method [executable=" + executable + ", processName=" + processName + "]");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").buildURL();
        ApplicationControlRequest request = new LaunchByExecutableRequest(executable, processName);
        return restTemplate.postForObject(url, request, ApplicationControlResponse.class).getWinDriverApplication();
    }

    public WinDriverApplication launchByExecutable(String executable, String processName, String arguments) {
        log.info("Launch app by executable method with arguments [" + arguments + "]");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").buildURL();
        ApplicationControlRequest request = new LaunchByExecutableRequest(executable, processName, arguments);
        return restTemplate.postForObject(url, request, ApplicationControlResponse.class).getWinDriverApplication();
    }

    public WinDriverApplication attachToProcessByProcessId(Integer processId) {
        log.info("Attach to process by process id [" + processId + "]");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").buildURL();
        ApplicationControlRequest request = new AttachToProcessByProcessIdRequest(processId);
        return restTemplate.postForObject(url, request, ApplicationControlResponse.class).getWinDriverApplication();
    }

    public WinDriverApplication attachToProcessByProcessName(String processName) {
        log.info("Attach to process by process name [" + processName + "]");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").buildURL();
        ApplicationControlRequest request = new AttachToProcessByProcessNameRequest(processName);
        return restTemplate.postForObject(url, request, ApplicationControlResponse.class).getWinDriverApplication();
    }

    public WinDriverApplication attachOrLaunchByProcessName(String processName) {
        log.info("Attach to process by process name [" + processName + "]");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").buildURL();
        ApplicationControlRequest request = new AttachOrLaunchByProcessNameRequest(processName);
        return restTemplate.postForObject(url, request, ApplicationControlResponse.class).getWinDriverApplication();
    }

    public void shutdown(String windriverId) {
        log.info("Closing app with windriverId '" + windriverId + "'");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").addSubItem(windriverId).buildURL();
        restTemplate.delete(url, String.class);
    }

    public ElementLocationControlResponse find(ElementLocationControlRequest request) {
        log.debug("Trying to find element using request: " + convertObjectToString(request));
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("AutomationElementLocations").buildURL();
        ResponseEntity<ElementLocationControlResponse> response = restTemplate.postForEntity(url, request, ElementLocationControlResponse.class);
        if (response.getBody().getPayload().isEmpty()) throw new WinDriverElementNotFoundException();
        return response.getBody();
    }

    public ActionControlResponse interact(ActionControlRequest request) {
        log.debug("Performing action using request: " + convertObjectToString(request));
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("AutomationElements").buildURL();
        return restTemplate.postForObject(url, request, ActionControlResponse.class);
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

    private ElementLocationControlResponse findWindowsByScopeOption(PropertyConditions conditions, SearchScopeOption scopeOption) {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(scopeOption);
        request.setParentWinDriverElementId("");
        request.setFindOption(FindOption.FIND_ALL);
        request.setConditionModels(conditions.getConditions());
        return find(request);
    }

    private ElementLocationControlResponse findWindowsByRootNode(PropertyConditions conditions, WinDriverElement root, SearchScopeOption scopeOption) {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(scopeOption);
        request.setParentWinDriverElementId(root.getElement().getWinDriverElementId());
        request.setFindOption(FindOption.FIND_ALL);
        request.setConditionModels(conditions.getConditions());
        return find(request);
    }

    public <T extends WinDriverElement> T getWindow(PropertyConditions conditions) {
        Map result = findWindowsByCondition(conditions).getPayload().get(0);
        return convertMapToWinDriverObject(result, winDriverElementType);
    }

    public <T extends WinDriverElement> T getWindowsByScopeOption (PropertyConditions conditions, SearchScopeOption scopeOption) {
        Map result = findWindowsByScopeOption(conditions, scopeOption).getPayload().get(0);
        return convertMapToWinDriverObject(result, winDriverElementType);
    }

    public <T extends WinDriverElement> T getWindowsByRootNode (PropertyConditions conditions, WinDriverElement root, SearchScopeOption scopeOption) {
        Map result = findWindowsByRootNode(conditions, root, scopeOption).getPayload().get(0);
        return convertMapToWinDriverObject(result, winDriverElementType);
    }

    public <T extends WinDriverElement> T convertMapToWinDriverObject(Map fromValue, Class<? extends WinDriverElement> cls) {
        T entity = objMapper.convertValue(fromValue, (Class<T>) cls);
        entity.initController(this);
        return entity;
    }

    public <T extends WinDriverElement> List<T> convertResultSet(List<Map> list, Class<? extends WinDriverElement> cls) {
        List<T> resultList = new ArrayList<>();
        for (Map result : list) resultList.add((T) convertMapToWinDriverObject(result, cls));
        return resultList;
    }

    public <T extends WinDriverElement> List<T> getWindows(PropertyConditions conditions) {
        List<Map> result = findWindowsByCondition(conditions).getPayload();
        return convertResultSet(result, winDriverElementType);
    }

    public <T extends WinDriverElement> List<T> getAllWindows() {
        PropertyConditions conditions = new PropertyConditions(Property.TRUE_CONDITION, "");
        List<Map> result = findWindowsByCondition(conditions).getPayload();
        return convertResultSet(result, winDriverElementType);
    }

    public ResponseEntity<String> healthCheck() {
        log.info("Performing health check request");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("HealthCheck").buildURL();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public void setLogLevel(Level logLevel) {
        log.info("Changing log level to '" + logLevel + "'");
        LogManager.getRootLogger().setLevel(logLevel);
    }
}
