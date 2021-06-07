package se.kth.iv1350.fantasticpos.util;

/**
 * An interface for classes that can print to a log. This interface does not handle log locations, it is
 *  up to the implementing class to decide where the log is.
 */
public interface Logger {

    /**
     * Prints the exception to a log.
     * @param exception The exception that will be logged.
     */
    void logException (Exception exception);

    /**
     * Prints the message to a log.
     * @param message The message that will be logged.
     */
    void printMessage (String message);
}
