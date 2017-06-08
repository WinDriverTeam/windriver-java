package com.windriver.util;

import com.windriver.core.EnvironmentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class PortFinder {

    private static final Logger log = LoggerFactory.getLogger(PortFinder.class);
    private static final Integer AGENT_MIN_PORT = EnvironmentProperties.AGENT_URL_PORT_MIN.readInteger();
    private static final Integer AGENT_MAX_PORT = EnvironmentProperties.AGENT_URL_PORT_MAX.readInteger();

    public static int findFreePort() {
        for (int i = AGENT_MIN_PORT; i <= AGENT_MAX_PORT; i++) {
            if (isAvailable(i)) {
                return i;
            }
        }
        String errorMsg = "Could not find an available port between " + AGENT_MIN_PORT + " and " + AGENT_MAX_PORT;
        RuntimeException exception = new RuntimeException(errorMsg);
        log.error(errorMsg, exception);
        throw exception;
    }

    private static boolean isAvailable(final int port) {
        ServerSocket serverSocket = null;
        DatagramSocket dataSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            dataSocket = new DatagramSocket(port);
            dataSocket.setReuseAddress(true);
            return true;
        } catch (final IOException e) {
            return false;
        } finally {
            if (dataSocket != null) {
                dataSocket.close();
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (final IOException e) {
                }
            }
        }
    }
}
