package com.windriver.model.request;

import com.windriver.model.automation.StartMethodNameOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationControlRequest {

    @JsonProperty
    private StartMethodNameOption methodName;
    @JsonProperty
    private String executable;
    @JsonProperty
    private String processName;

    public ApplicationControlRequest(StartMethodNameOption methodName, String executable, String processName) {
        this.methodName = methodName;
        this.executable = executable;
        this.processName = processName;
    }

    public StartMethodNameOption getMethodName() {
        return methodName;
    }

    public void setMethodName(StartMethodNameOption methodName) {
        this.methodName = methodName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ApplicationControlRequest that = (ApplicationControlRequest) o;

        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null)
            return false;
        if (executable != null ? !executable.equals(that.executable) : that.executable != null)
            return false;
        return processName != null ? processName.equals(that.processName) : that.processName == null;

    }

    @Override
    public int hashCode() {
        int result = methodName != null ? methodName.hashCode() : 0;
        result = 31 * result + (executable != null ? executable.hashCode() : 0);
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationControlRequest{" +
                "methodName='" + methodName + '\'' +
                ", executable='" + executable + '\'' +
                ", processName='" + processName + '\'' +
                '}';
    }
}
