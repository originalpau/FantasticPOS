package se.kth.iv1350.fantasticpos.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * This class is responsible for showing error messages to the user.
 */
class ErrorMessageHandler {

    /**
     * Displays the specified error message.
     *
     * @param msg The error message.
     */
    void showErrorMsg(String msg) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append("| " + createTime() + " | ");
        errorMsgBuilder.append("ERROR ");
        errorMsgBuilder.append(msg);
        errorMsgBuilder.append("\n");
        System.out.println(errorMsgBuilder);
    }

    private String createTime() {
        String europeanDateTimePattern = "dd MMM uuuu HH:mm:ss";
        DateTimeFormatter europeanFormatter = DateTimeFormatter.ofPattern(europeanDateTimePattern);
        LocalDateTime now = LocalDateTime.now();
        return europeanFormatter.format(now);
    }
}
