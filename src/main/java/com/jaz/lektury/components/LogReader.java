package com.jaz.lektury.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;


@Component
public class LogReader {
    private final boolean DEBUG = false;
    private final String LOG_PATH = System.getProperty("user.dir") + "/logs/application.log";
    private final Logger logger = LogManager.getLogger(LogReader.class);

    private String logData;


    public String readLogs(Optional<String> level) {
        String logLevelStr = level.orElse("debug");
        Integer logLevel = getLogLevel(logLevelStr);

        logger.info("Reading logs at {}", LocalDateTime::now);

        try {
            logData = "";
            Scanner reader = new Scanner(new File(LOG_PATH));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                Integer lineLogLevel = getLogLevel(extractLogLevelFromStr(line));
                if (lineLogLevel <= logLevel)
                    logData = logData.concat(line + "<br>");
            }
        } catch (NullPointerException | FileNotFoundException e) {
            if (DEBUG) e.printStackTrace();
            logger.error("An internal error occurred while reading logs. Turn on DEBUG mode and see console output for more information.");
            logData = logData.concat("An internal error occurred.");
        }

        return logData;
    }

    private String extractLogLevelFromStr(String line) {
        String cut = line.substring(24);
        return cut.substring(0, cut.indexOf(" "));
    }

    private Integer getLogLevel(String logLevelStr) {
        return switch (logLevelStr.toLowerCase()) {
            case "all" -> Integer.MAX_VALUE;
            case "trace" -> 600;
            case "debug" -> 500;
            case "info" -> 400;
            case "warn" -> 300;
            case "error" -> 200;
            case "fatal" -> 100;
            case "off" -> 0;
            default -> 500;
        };
    }
}
