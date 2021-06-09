package se.kth.iv1350.fantasticpos.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    TotalRevenueView instance;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        instance = new TotalRevenueView();
    }

    @AfterEach
    void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    void testDoShowTotalRevenue() {
        instance.newSale(500.0);
        instance.newSale(50);
        double totalRevenue = 500.0 + 50;
        String expResult = "TOTAL REVENUE: " + Math.round(totalRevenue * 100)/100.0 + " SEK";
        String result = outContent.toString();
        assertTrue(result.contains(expResult), "Wrong printout of total revenue");
    }
}