package ua.windriver.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.windriver.model.automation.StartMethodNameOption;

public class AttachOrLaunchByProcessNameRequest extends ApplicationControlRequest {

    @JsonProperty
    private String processName;

    public AttachOrLaunchByProcessNameRequest(String processName) {
        setMethodName(StartMethodNameOption.ATTACH_OR_LAUNCH_BY_PROCESS_NAME);
        this.processName = processName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
