package com.windriver.core;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class ConfiguredRequestFactory {

    private static final Integer READ_TIMEOUT = EnvironmentProperties.HTTP_REQUEST_READ_TIMEOUT.readInteger();
    private static final Integer CONNECT_TIMEOUT = EnvironmentProperties.HTTP_REQUEST_CONNECT_TIMEOUT.readInteger();

    public static ClientHttpRequestFactory defaultFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        return factory;
    }
}
