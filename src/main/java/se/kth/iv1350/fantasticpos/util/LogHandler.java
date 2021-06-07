package se.kth.iv1350.fantasticpos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 */
public class LogHandler implements Logger{
    private static final String LOG_FILE_NAME = "sale-log.txt";
    private PrintWriter logFile;

    /**
     * Creates a new instance.
     * @throws IOException if unable to log.
     */
    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception The exception that shall be logged.
     */
    @Override
    public void logException (Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append("| " + createTime() + " |");
        logMsgBuilder.append("Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        exception.printStackTrace(logFile);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        String dateTimePattern = "uuuu/MM/dd HH:mm:ss";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimePattern);
        return format.format(now);
    }

    /**
     * Writes the specific message to the log.
     *
     * @param message The message that will be logged.
     */
    @Override
    public void printMessage (String message) {
        String msg = ("ERROR | " + message + "\n" + createTime() + "\n");
        logFile.println(msg);
    }
}
