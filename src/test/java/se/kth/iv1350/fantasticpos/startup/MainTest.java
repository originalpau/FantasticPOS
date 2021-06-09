package se.kth.iv1350.fantasticpos.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MainTest {

    @Test
    public void testMain() {
        PrintStream originalSysOut = null;
        try {
            originalSysOut = System.out;
            ByteArrayOutputStream printoutBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(printoutBuffer));
            String[] args = null;
            Main.main(args);

            String expectedSaleStart = "STARTED";
            String expScanStart = "---------------- Start of scanning Procedure ----------------";
            String expScanEnd = "---------------- End of scanning procedure ------------------\n";
            String expSaleInfo = "Scanning item...";
            String expSaleEnd = "End Sale\nTOTAL SUM: ";
            String receipt = createStoreInfo();

            String outContent = printoutBuffer.toString();
            assertTrue(outContent.contains(expectedSaleStart), "Wrong output when main is executed.");
            assertTrue(outContent.contains(expScanStart), "Wrong output when main is executed.");
            assertTrue(outContent.contains(expScanEnd), "Wrong output when main is executed.");
            assertTrue(outContent.contains(expSaleInfo), "Wrong output when main is executed.");
            assertTrue(outContent.contains(expSaleEnd), "Wrong output when main is executed.");
            assertTrue(outContent.contains(receipt), "Wrong output when main is executed.");
        } finally {
            System.setOut(originalSysOut);
        }
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
