package se.kth.iv1350.fantasticpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.ItemDTO;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    private Sale paidSale;
    private Receipt instance;

    @BeforeEach
    void setUp() {
        paidSale = new Sale();
        ItemDTO foundItem = new ItemDTO("Banana", 100, 5, 0.12);
        int TEN_ITEM_QUANTITY = 10;
        paidSale.registerItem(foundItem, TEN_ITEM_QUANTITY);
        double paidAmt = 200;
        paidSale.pay(paidAmt);
        instance = new Receipt(paidSale);
    }

    @AfterEach
    void tearDown() {
        paidSale = null;
        instance = null;
    }

    @Test
    void testCreateReceiptString() {
        String expStoreInfo = createStoreInfo();
        LocalDateTime timeOfSale = LocalDateTime.now();
        String expItemsTitle = "Item";
        String expTax = "VAT";
        String expChange = "Change";
        Double expTotal = paidSale.getTotalPrice();

        String result = instance.createReceiptString();

        assertTrue(result.contains(expStoreInfo), "Wrong Store Info");
        assertTrue(result.contains(Integer.toString(timeOfSale.getYear())), "Wrong sale year.");
        assertTrue(result.contains(Integer.toString(timeOfSale.getMonthValue())),
                "Wrong rental month.");
        assertTrue(result.contains(Integer.toString(timeOfSale.getDayOfMonth())),
                "Wrong rental day.");
        assertTrue(result.contains(Integer.toString(timeOfSale.getHour())), "Wrong rental hour.");
        assertTrue(result.contains(Integer.toString(timeOfSale.getMinute())), "Wrong rental minute.");
        assertTrue(result.contains(expItemsTitle),"Wrong Item Title.");
        assertTrue(result.contains(expChange), "Wrong change.");
        assertTrue(result.contains(expTax),"Wrong VAT.");
        assertTrue(result.contains(expTotal.toString()),"Wrong Total Price.");
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