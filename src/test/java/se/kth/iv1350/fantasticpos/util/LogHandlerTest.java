package se.kth.iv1350.fantasticpos.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.NonexistentIdentifierException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class LogHandlerTest {
    private LogHandler instance;
    private String logFileName = "sale-log.txt";

    @BeforeEach
    public void createInstance() {
        instance = LogHandler.getLogHandler();
    }

    @AfterEach
    public void deleteFile() {
        instance = null;
        File logFile = new File(logFileName);
        logFile.delete();
    }

    @Test
    public void testPrintMsg() throws IOException {
        String msg = "The message to log";
        instance.printMessage(msg);
        String expResultMsg = ("ERROR | " + msg);

        LocalDateTime now = LocalDateTime.now();
        String dateTimePattern = "uuuu/MM/dd HH:mm:ss";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimePattern);
        String expResultDateTime = format.format(now);

        assertTrue(fileContains(expResultDateTime), "Wrong printout.");
        assertTrue(fileContains(expResultMsg), "Wrong printout.");
    }

    @Test
    public void testLog() throws IOException {
        NonexistentIdentifierException exception = new NonexistentIdentifierException(-1);
        instance.logException(exception);
        String expResultMsg = "Unable to find -1 because it does not exist in the inventory catalog.";

        LocalDateTime now = LocalDateTime.now();
        String dateTimePattern = "uuuu/MM/dd HH:mm:ss";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimePattern);
        String expResultTime = format.format(now);

        String expResultStack = "at se.kth.iv1350.fantasticpos.util.LogHandlerTest.testLog";
        assertTrue(fileContains(expResultMsg), "Wrong printout.");
        assertTrue(fileContains(expResultTime), "Wrong printout.");
        assertTrue(fileContains(expResultStack), "Wrong printout.");
    }

    private boolean fileContains(String searchedString) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(logFileName));
        String line = null;
        while ((line = file.readLine()) != null) {
            if (line.contains(searchedString)) {
                return true;
            }
        }
        return false;
    }
}
