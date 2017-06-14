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
import ua.windriver.model.request.ActionControlRequest;
import ua.windriver.model.request.ApplicationControlRequest;
import ua.windriver.model.request.ElementLocationControlRequest;
import ua.windriver.model.response.ActionControlResponse;
import ua.windriver.model.response.ApplicationControlResponse;
import ua.windriver.model.response.ElementLocationControlResponse;
import ua.windriver.util.PropertyConditions;
import ua.windriver.util.URLBuilder;

import java.util.List;

public class WinDriverService {

    private static final Logger log = LoggerFactory.getLogger(WinDriverService.class);
    private RestTemplate restTemplate;
    private URLBuilder urlBuilder;
    private ObjectMapper objMapper;

    public WinDriverService() {
        urlBuilder = new URLBuilder();
        objMapper = new ObjectMapper();
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

    private String convertObjectToString(Object obj) {
        try {
            return objMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Cannot convert value");
        }
        return null;
    }

    public WinDriverApplication launch(StartMethodNameOption methodName, String executable, String processName) {
        log.info("Launching app with arguments [" + methodName + ", " + executable + ", " + processName + "]");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").buildURL();
        ApplicationControlRequest request = new ApplicationControlRequest(methodName, executable, processName);
        return restTemplate.postForObject(url, request, ApplicationControlResponse.class).getWinDriverApplication();
    }

    public void shutdown(String windriverId) {
        log.info("Closing app with windriverId '" + windriverId + "'");
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("Applications").addSubItem(windriverId).buildURL();
        restTemplate.delete(url, String.class);
    }

    public ElementLocationControlResponse find(ElementLocationControlRequest request) {
        log.info("Trying to find element using request: " +convertObjectToString(request));
        String url = urlBuilder.addAgentHeadURL().addSubItem("WinAuto").addSubItem("AutomationElementLocations").buildURL();
        ResponseEntity<ElementLocationControlResponse> response = restTemplate.postForEntity(url, request, ElementLocationControlResponse.class);

        List<WinDriverElement> resultList = response.getBody().getWinDriverElements();
        if (resultList.isEmpty()) throw new WinDriverElementNotFoundException();
        else resultList.forEach(element -> element.initController(this));

        return response.getBody();
    }

    public ActionControlResponse interact(ActionControlRequest request) {
        log.info("Performing action using request: " + convertObjectToString(request));
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

    public WinDriverElement getWindow(PropertyConditions conditions) {
        return findWindowsByCondition(conditions).getWinDriverElements().get(0);
    }

    public List<WinDriverElement> getWindows(PropertyConditions conditions) {
        return findWindowsByCondition(conditions).getWinDriverElements();
    }

    public List<WinDriverElement> getAllWindows() {
        PropertyConditions conditions = new PropertyConditions(Property.TRUE_CONDITION, "");
        return findWindowsByCondition(conditions).getWinDriverElements();
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
