package ua.windriver.util;

import ua.windriver.core.EnvironmentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class URLBuilder {

    private static final Logger log = LoggerFactory.getLogger(URLBuilder.class);
    private StringBuilder folders;
    private StringBuilder params;

    public URLBuilder() {
        folders = new StringBuilder();
        params = new StringBuilder();
    }

    public URLBuilder addSubItem(String item) {
        folders.append("/").append(item);
        return this;
    }

    public URLBuilder addParameter(String parameter, String value) {
        if (params.toString().length() > 0) {
            params.append("&");
        }
        params.append(parameter).append("=").append(value);
        return this;
    }

    public String buildURL() {
        try {
            URI uri = new URI(null, null, folders.toString(), params.toString(), null);
            return uri.toString();
        } catch (URISyntaxException e) {
            log.error("Can't create URL");
        } finally {
            folders.setLength(0);
            params.setLength(0);
        }
        return null;
    }

    public URLBuilder addAgentHeadURL() {
        folders.append(EnvironmentProperties.AGENT_URL_CONNECTION_TYPE.readString()).append("://")
                .append(EnvironmentProperties.AGENT_URL_HOST.readString()).append(":")
                .append(EnvironmentProperties.AGENT_URL_PORT.readString());
        return this;
    }
}
