package com.windriver.model.response;

import com.windriver.model.automation.WinDriverApplication;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationControlResponse {

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Entity")
    private WinDriverApplication winDriverApplication;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WinDriverApplication getWinDriverApplication() {
        return winDriverApplication;
    }

    public void setWinDriverApplication(WinDriverApplication winDriverApplication) {
        this.winDriverApplication = winDriverApplication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ApplicationControlResponse response = (ApplicationControlResponse) o;

        if (message != null ? !message.equals(response.message) : response.message != null)
            return false;
        return winDriverApplication != null ?
                winDriverApplication.equals(response.winDriverApplication) :
                response.winDriverApplication == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (winDriverApplication != null ? winDriverApplication.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationControlResponse{" +
                "message='" + message + '\'' +
                ", winDriverApplication=" + winDriverApplication +
                '}';
    }
}
