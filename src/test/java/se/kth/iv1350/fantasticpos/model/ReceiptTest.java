package se.kth.iv1350.fantasticpos.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @Test
    void testCreateReceiptString() {
        ItemDTO foundItem = new ItemDTO("banana", 100, 5, 0.12);
        int itemQuantity = 10;
        double paidAmt = 200;
        Sale paidSale = new Sale();
        paidSale.registerItem(foundItem, itemQuantity);
        paidSale.pay(paidAmt);
        Receipt receipt = new Receipt(paidSale);
        String expResultTax = "VAT";
        String expResultChange = "Change";
        String result = receipt.createReceiptString();
        assertTrue(result.contains(expResultChange), "Wrong change.");
        assertTrue(result.contains(expResultTax),"Wrong VAT.");
    }
}