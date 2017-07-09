package ua.windriver.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementLocationControlResponse {

    @JsonProperty("AutomationElementWrappers")
    private List<Map> payload;

    public List<Map> getPayload() {
        return payload;
    }
    public void setPayload(List<Map> payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementLocationControlResponse response = (ElementLocationControlResponse) o;

        return payload != null ? payload.equals(response.payload) : response.payload == null;

    }

    @Override
    public int hashCode() {
        return payload != null ? payload.hashCode() : 0;
    }
}
