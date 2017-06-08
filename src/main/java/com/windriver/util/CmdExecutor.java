package com.windriver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CmdExecutor {

    private static final Logger log = LoggerFactory.getLogger(CmdExecutor.class);
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Process process;

    public void execute(String... commands) throws InterruptedException, IOException {
        log.info("Launch process in cmd with arguments: " + Arrays.asList(commands));
        ProcessBuilder pb = new ProcessBuilder(commands);
        Runnable runnable = () -> {
            try {
                process = pb.start();
            } catch (IOException e) {
                log.error("Cannot start process with arguments " + Arrays.asList(commands), e);
            }
        };

        executor.submit(runnable);
        // needed to start process
        Thread.sleep(500);
    }

    public void closeProcess() throws IOException {
        try {
            process.destroyForcibly();
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Process was interrupted by Executor", e);
        } finally {
            executor.shutdownNow();
            log.info("Agent closed successfully");
        }

        log.debug("Agent logs: \n" + InputStreamConverter.convert(process.getInputStream()));
    }
}
