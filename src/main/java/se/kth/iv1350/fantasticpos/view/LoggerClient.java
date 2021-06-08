package se.kth.iv1350.fantasticpos.view;

import se.kth.iv1350.fantasticpos.util.LogHandler;
import se.kth.iv1350.fantasticpos.util.Logger;

/**
 * A client for the logger. Prints log messages to the specified logger.
 */
class LoggerClient {
    private Logger logger;

    /**
     * Logs exception to a file.
     * @param exception The exception that will be logged.
     */
    void logExceptionToFile (Exception exception) {
        setLogger(LogHandler.getLogHandler());
        logger.logException(exception);
    }

    /**
     * Prints exception to the console.
     * @param exception The exception that will be printed.
     */
    void printExceptionToConsole (Exception exception) {
        setLogger(new ErrorMessageHandler());
        logger.logException(exception);
    }

    /**
     * Prints message to the console.
     * @param message THe message that will be printed.
     */
    void printMessageToConsole (String message) {
        setLogger(new ErrorMessageHandler());
        logger.printMessage(message);
    }

    /**
     * Logs message to the console and the file.
     * @param message The message that will be logged.
     */
    void writeMessageToConsoleAndFile (String message) {
        printMessageToConsole(message);
        logMessageToFile(message);
    }

    /**
     * Prints message to console and logs exception to file.
     * @param msg The message that will be printed.
     * @param exc The exception that will be logged.
     */
    void writeToConsoleAndFile (String msg, Exception exc) {
        printMessageToConsole(msg);
        logExceptionToFile(exc);
    }

    private void logMessageToFile (String message) {
        setLogger(LogHandler.getLogHandler());
        logger.printMessage(message);
    }

    private void setLogger (Logger logger) {
        this.logger = logger;
    }
}
