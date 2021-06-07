package se.kth.iv1350.fantasticpos.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.NonexistentIdentifierException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorMessageHandlerTest {
    ErrorMessageHandler instance = new ErrorMessageHandler();
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        instance = new ErrorMessageHandler();
    }

    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    public void testPrintMsg() {
        String errorMsg = "this is the message";
        instance.printMessage(errorMsg);
        String expResultMsg = "ERROR | " + errorMsg;

        String europeanDateTimePattern = "dd MMM uuuu HH:mm:ss";
        DateTimeFormatter europeanFormatter = DateTimeFormatter.ofPattern(europeanDateTimePattern);
        LocalDateTime now = LocalDateTime.now();
        String expResultTime = europeanFormatter.format(now);

        String result = outContent.toString();
        assertTrue(result.contains(expResultMsg), "Wrong printout.");
        assertTrue(result.contains(expResultTime), "Wrong printout.");
    }

    @Test
    public void testLogException() {
        NonexistentIdentifierException exception = new NonexistentIdentifierException(-1);
        instance.logException(exception);
        String expResultMsg = "Unable to find -1 because it does not exist in the inventory catalog.";

        LocalDateTime now = LocalDateTime.now();
        String dateTimePattern = "dd MMM uuuu HH:mm:ss";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimePattern);
        String expResultTime = format.format(now);

        String result = outContent.toString();
        assertTrue(result.contains(expResultMsg),"Wrong printout.");
        assertTrue(result.contains(expResultTime), "Wrong printout");
    }
}