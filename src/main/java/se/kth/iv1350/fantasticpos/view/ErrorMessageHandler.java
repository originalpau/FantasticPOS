package se.kth.iv1350.fantasticpos.view;

import se.kth.iv1350.fantasticpos.util.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * This class is responsible for showing error messages to the user.
 */
class ErrorMessageHandler implements Logger {

    /**
     * Displays the specified error message.
     *
     * @param exception The error message.
     */
    @Override
    public void logException (Exception exception) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append("ERROR | ");
        errorMsgBuilder.append(exception.getMessage());
        errorMsgBuilder.append("\n");
        errorMsgBuilder.append(createTime() + "\n");
        System.out.println(errorMsgBuilder);
    }

    /**
     * Prints the message to the console.
     *
     * @param message The message that will be logged.
     */
    @Override
    public void printMessage (String message) {
        System.out.println("ERROR | " + message + "\n" + createTime() + "\n");
    }

    private String createTime() {
        String europeanDateTimePattern = "dd MMM uuuu HH:mm:ss";
        DateTimeFormatter europeanFormatter = DateTimeFormatter.ofPattern(europeanDateTimePattern);
        LocalDateTime now = LocalDateTime.now();
        return europeanFormatter.format(now);
    }
}
