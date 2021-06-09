package se.kth.iv1350.fantasticpos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.NonexistentIdentifierException;
import se.kth.iv1350.fantasticpos.integration.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class OperationFailedExceptionTest {
    private Controller controller;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @BeforeEach
    void setup() {
        Printer printer = new Printer();
        controller = new Controller(printer);
        controller.startSale();
    }

    @AfterEach
    void tearDown() {
        controller = null;

        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    void OperationFailedExceptionTest() {
        final int DATABASE_FAILURE = 1337;
        Printer printer = new Printer();
        Controller controller = new Controller(printer);
        try {
            controller.scanItem(DATABASE_FAILURE, 1);
            fail("Managed to scan when there is no database connection.");
        } catch (NonexistentIdentifierException e) {
            fail("Got exception.");
            e.printStackTrace();
        } catch(OperationFailedException e) {
            assertTrue(e.getMessage().contains("Could not scan the identifier."),
                    "Wrong exception message" + e.getMessage());
        }
    }
}