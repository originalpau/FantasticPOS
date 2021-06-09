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
        Controller contr = new Controller(printer);
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
        String expectedSaleStart = "STARTED";
        String expScanStart = "---------------- Start of scanning Procedure ----------------";
        String expScanEnd = "---------------- End of scanning procedure ------------------\n";
        String expSaleInfo = "Scanning item...";
        String expSaleEnd = "End Sale\nTOTAL SUM: ";
        String receipt = createStoreInfo();

        assertTrue(printout.contains(expectedSaleStart), "UI did not start correctly.");
        assertTrue(printout.contains(expScanStart), "UI did not start correctly.");
        assertTrue(printout.contains(expScanEnd), "UI did not start correctly.");
        assertTrue(printout.contains(expSaleInfo), "UI did not start correctly.");
        assertTrue(printout.contains(expSaleEnd), "UI did not start correctly.");
        assertTrue(printout.contains(receipt), "UI did not start correctly.");
    }

    private String createStoreInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("\n%-12s %-35s", "", "Paulina Huang's Store"));
        endSection(builder);
        builder.append(String.format("%-6s %-40s", "", "KTH Royal Institute of Technology"));
        endSection(builder);
        return builder.toString();
    }

    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }
}
