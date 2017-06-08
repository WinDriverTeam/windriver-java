package com.windriver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class SessionIdHeaderInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SessionIdHeaderInterceptor.class);
    private static final String SESSION_ID_KEY = "windriver_sessionId";
    private String sessionIdValue;

    public SessionIdHeaderInterceptor(String sessionId) {
        this.sessionIdValue = sessionId;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(SESSION_ID_KEY, sessionIdValue);
        log.debug("Request headers: " + request.getHeaders() + " Request body: " + new String(body));
        return execution.execute(request, body);
    }
}
