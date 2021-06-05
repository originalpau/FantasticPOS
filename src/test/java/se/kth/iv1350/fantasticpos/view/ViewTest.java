package se.kth.iv1350.fantasticpos.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.fantasticpos.controller.Controller;
import se.kth.iv1350.fantasticpos.integration.Printer;
import se.kth.iv1350.fantasticpos.integration.RegistryCreator;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() throws IOException {
        Printer printer = new Printer();
        RegistryCreator creator = new RegistryCreator();
        Controller contr = new Controller(creator, printer);
        instanceToTest = new View(contr);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;

        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunFakeExecution() {
        instanceToTest.runSampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "STARTED";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly.");
    }
}
