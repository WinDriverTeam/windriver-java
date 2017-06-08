package com.windriver.core;

import com.windriver.util.PropertiesController;

public enum EnvironmentProperties {

    HUB_URL_CONNECTION_TYPE,
    HUB_URL_HOST,
    HUB_URL_PORT,

    ELEMENT_LOCATION_RESPONSE_CACHE_MAX_SIZE,
    ELEMENT_LOCATION_RESPONSE_CACHE_TTL_MINUTES,

    HTTP_REQUEST_READ_TIMEOUT,
    HTTP_REQUEST_CONNECT_TIMEOUT,
    CLIENT_LOG_LEVEL,
    AGENT_URL_CONNECTION_TYPE,
    AGENT_URL_HOST,
    AGENT_URL_PORT_MIN,
    AGENT_URL_PORT_MAX,;

    private String getPropertyName() {
        return this.name().toLowerCase().replaceAll("_", ".");
    }

    public String readString() {
        return PropertiesController.getProperty(getPropertyName());
    }

    public Long readLong() {
        return Long.valueOf(PropertiesController.getProperty(getPropertyName()));
    }

    public Integer readInteger() {
        return Integer.valueOf(PropertiesController.getProperty(getPropertyName()));
    }
}