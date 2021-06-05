package se.kth.iv1350.fantasticpos.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorMessageHandlerTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @BeforeEach
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams() {
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testShowErrorMessage() {
        String errorMsg = "this is the message";
        ErrorMessageHandler instance = new ErrorMessageHandler();
        instance.showErrorMsg(errorMsg);
        String expResultMsg = ", ERROR: " + errorMsg;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String expResultTime = now.format(formatter);
        String result = outContent.toString();
        assertTrue(result.contains(expResultMsg), "Wrong printout.");
        assertTrue(result.contains(expResultTime), "Wrong printout.");
    }
}