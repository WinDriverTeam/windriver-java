package com.windriver.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailableNodesResponse {

    @JsonProperty("AllAvailableNodes")
    private HashMap<String, String> availableNodes;

    public HashMap<String, String> getAvailableNodes() {
        return availableNodes;
    }

    public void setAvailableNodes(HashMap<String, String> availableNodes) {
        this.availableNodes = availableNodes;
    }

    @Override
    public String toString() {
        return "AvailableNodesResponse{" +
                "availableNodes=" + availableNodes +
                '}';
    }
}
