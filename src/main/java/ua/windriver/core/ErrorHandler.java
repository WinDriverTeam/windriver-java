package ua.windriver.core;

import ua.windriver.model.exception.WinDriverAgentException;
import ua.windriver.util.InputStreamConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class ErrorHandler extends DefaultResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);
    private static final String CHARSET = "UTF-8";

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String responseBody = InputStreamConverter.convert(response.getBody(), CHARSET);
        log.error("Error on agent side: " + responseBody);
        throw new WinDriverAgentException(responseBody);
    }
}
