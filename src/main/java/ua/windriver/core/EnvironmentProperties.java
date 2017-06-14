package ua.windriver.core;

import ua.windriver.util.PropertiesController;

public enum EnvironmentProperties {

    AGENT_URL_CONNECTION_TYPE,
    AGENT_URL_HOST,
    AGENT_URL_PORT,
    HTTP_REQUEST_READ_TIMEOUT,
    HTTP_REQUEST_CONNECT_TIMEOUT,
    CLIENT_LOG_LEVEL;


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