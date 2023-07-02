package com.DataOrg.logger;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class FileLogger {

    private Logger logger;
    private FileHandler fileHandler;

    public FileLogger() {
        try {
            // Initialize the logger
            logger = Logger.getLogger(FileLogger.class.getName());

            // Set up file handler to log to the specified file
            int limit = 10000000; // 10 Mb
            int numLogFiles = 3;
            fileHandler = new FileHandler("/home/barath/Desktop/servlet/DataOrg/application.log",limit,numLogFiles);
            fileHandler.setFormatter(new SimpleFormatter());

            // Set the desired log level
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logWarning(String message) {
        logger.warning(message);
    }

    public void logSevere(String message) {
        logger.severe(message);
    }

    public void close() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }
}
