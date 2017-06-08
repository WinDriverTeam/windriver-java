package com.windriver.model.response;

import com.windriver.model.automation.WinDriverElement;
import com.windriver.model.request.ElementLocationControlRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementLocationControlResponse {

    @JsonProperty("AutomationElementWrappers")
    private List<WinDriverElement> winDriverElements;

    public List<WinDriverElement> getWinDriverElements() {
        return winDriverElements;
    }

    public void setWinDriverElements(List<WinDriverElement> winDriverElements) {
        this.winDriverElements = winDriverElements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementLocationControlResponse response = (ElementLocationControlResponse) o;

        return winDriverElements != null ? winDriverElements.equals(response.winDriverElements) : response.winDriverElements == null;

    }

    @Override
    public int hashCode() {
        return winDriverElements != null ? winDriverElements.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ElementLocationControlResponse{" +
                "winDriverElements=" + winDriverElements +
                '}';
    }
}
