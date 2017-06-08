package com.windriver.model.automation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WinDriverApplication {

    private String methodName;
    private String processName;
    private String executable;
    @JsonProperty("WinDriverElementId")
    private String winDriverElementId;

    public WinDriverApplication() {
    }

    public String getWinDriverElementId() {
        return winDriverElementId;
    }

    public void setWinDriverElementId(String winDriverElementId) {
        this.winDriverElementId = winDriverElementId;
    }

    public String getExecutable() {
        return executable;
    }

    public String getProcessName() {
        return processName;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        WinDriverApplication that = (WinDriverApplication) o;

        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null)
            return false;
        if (processName != null ? !processName.equals(that.processName) : that.processName != null)
            return false;
        return winDriverElementId != null ? winDriverElementId.equals(that.winDriverElementId) : that.winDriverElementId == null;

    }

    @Override
    public int hashCode() {
        int result = methodName != null ? methodName.hashCode() : 0;
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        result = 31 * result + (winDriverElementId != null ? winDriverElementId.hashCode() : 0);
        return result;
    }
}
