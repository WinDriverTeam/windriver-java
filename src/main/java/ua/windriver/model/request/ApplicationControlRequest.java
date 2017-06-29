package ua.windriver.model.request;

import ua.windriver.model.automation.StartMethodNameOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ApplicationControlRequest {

    @JsonProperty
    private StartMethodNameOption methodName;

    public StartMethodNameOption getMethodName() {
        return methodName;
    }

    public void setMethodName(StartMethodNameOption methodName) {
        this.methodName = methodName;
    }
}
