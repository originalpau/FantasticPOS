package se.kth.iv1350.fantasticpos.util;

import se.kth.iv1350.fantasticpos.view.ErrorMessageHandler;

import java.io.IOException;

/**
 * A client for the logger. Prints log messages to the specified logger.
 */
public class LoggerClient {
    private Logger logger;

    /**
     * Logs exception to a file.
     * @param exception The exception that will be logged.
     */
    public void logExceptionToFile (Exception exception) {
        try {
            setLogger(new LogHandler());
        } catch (IOException e) {
            throw new RuntimeException("Unable to log.");
        }
        logger.logException(exception);
    }

    /**
     * Prints a exception to the console.
     * @param exception The exception that will be printed.
     */
    public void printExceptionToConsole (Exception exception) {
        setLogger(new ErrorMessageHandler());
        logger.logException(exception);
    }

    /**
     * Prints a message to the console.
     * @param message THe message that will be printed.
     */
    public void printMessageToConsole (String message) {
        setLogger(new ErrorMessageHandler());
        logger.printMessage(message);
    }

    /**
     * Logs a message to the console and the file.
     * @param message The message that will be logged.
     */
    public void writeMessageToConsoleAndFile (String message) {
        printMessageToConsole(message);
        logMessageToFile(message);
    }

    private void logMessageToFile (String message) {
        try {
            setLogger(new LogHandler());
        } catch (IOException e) {
            throw new RuntimeException("Unable to log.");
        }
        logger.printMessage(message);
    }

    private void setLogger (Logger logger) {
        this.logger = logger;
    }
}
