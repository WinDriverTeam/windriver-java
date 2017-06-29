package ua.windriver.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.windriver.model.automation.StartMethodNameOption;

public class LaunchByExecutableRequest extends ApplicationControlRequest {

    @JsonProperty
    private String executable;
    @JsonProperty
    private String processName;
    @JsonProperty
    private String arguments;

    public LaunchByExecutableRequest(String executable, String processName, String arguments) {
        setMethodName(StartMethodNameOption.LAUNCH_BY_EXECUTABLE);
        this.executable = executable;
        this.processName = processName;
        this.arguments = arguments;
    }

    public LaunchByExecutableRequest(String executable, String processName) {
        setMethodName(StartMethodNameOption.LAUNCH_BY_EXECUTABLE);
        this.executable = executable;
        this.processName = processName;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
