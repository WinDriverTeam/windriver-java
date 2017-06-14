package ua.windriver.model.request;

import ua.windriver.model.automation.Action;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionControlRequest {

    @JsonProperty("WinDriverElementId")
    private String winDriverElementId;
    @JsonProperty("Action")
    private Action action;
    @JsonProperty("Value")
    private String value;

    public ActionControlRequest() {
    }

    public String getWinDriverElementId() {
        return winDriverElementId;
    }

    public void setWinDriverElementId(String winDriverElementId) {
        this.winDriverElementId = winDriverElementId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ActionControlRequest request = (ActionControlRequest) o;

        if (winDriverElementId != null ?
                !winDriverElementId.equals(request.winDriverElementId) :
                request.winDriverElementId != null)
            return false;
        if (action != null ? !action.equals(request.action) : request.action != null)
            return false;
        return value != null ? value.equals(request.value) : request.value == null;

    }

    @Override
    public int hashCode() {
        int result = winDriverElementId != null ? winDriverElementId.hashCode() : 0;
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionControlRequest{" +
                "winDriverElementId='" + winDriverElementId + '\'' +
                ", action='" + action + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
