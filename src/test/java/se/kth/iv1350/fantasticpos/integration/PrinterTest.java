package se.kth.iv1350.fantasticpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.model.Receipt;
import se.kth.iv1350.fantasticpos.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

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
    void testPrintReceipt() {
        ItemDTO foundItem = new ItemDTO("banana", 100, 5, 0.12);
        int itemQuantity = 10;
        double paidAmt = 200;
        Sale paidSale = new Sale();
        paidSale.registerItem(foundItem, itemQuantity);
        paidSale.pay(paidAmt);
        Receipt receipt = new Receipt(paidSale);
        Printer printer = new Printer();
        printer.printReceipt(receipt);
        String expResultTax = "VAT";
        String expResultChange = "Change";
        String result = outContent.toString();
        assertTrue(result.contains(expResultChange), "Wrong Change");
        assertTrue(result.contains(expResultTax), "Wrong VAT");
    }
}
