package se.kth.iv1350.fantasticpos.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {

    @Test
    void testCalculateRunningTotal() {
        ItemDTO firstItem = new ItemDTO("razor", 1, 59, 0.25);
        ItemDTO secondItem = new ItemDTO("chicken", 2, 79, 0.12);
        ItemDTO thirdItem =  new ItemDTO("chicken", 2, 79, 0.12);
        int quantityOfItem = 1;

        ItemList itemList = new ItemList();
        itemList.logItem(firstItem, quantityOfItem);
        itemList.logItem(secondItem, quantityOfItem);
        itemList.logItem(thirdItem, quantityOfItem);

        Balance balance = new Balance();
        double result = balance.calculateRunningTotal(itemList.getItems());
        double expResult = ((59 + 59*0.25) * 1) + ((79 + 79*0.12) * 2);
        assertEquals(expResult, result, "Total Price is not correct.");
    }

    @Test
    void testCalculateChange() {
        ItemDTO firstItem = new ItemDTO("razor", 1, 59, 0.25);
        int itemQuantity = 1;

        ItemList itemList = new ItemList();
        itemList.logItem(firstItem, itemQuantity);

        Balance balance = new Balance();
        double totalPrice = balance.calculateRunningTotal(itemList.getItems());
        double paidAmt = 500;
        double change = balance.calculateChange(paidAmt, totalPrice);

        double expResult = Math.round(paidAmt - totalPrice);
        assertEquals(expResult, change, "Change is not correct.");
    }

    @Test
    void testCalculateTaxAmt() {
        ItemDTO firstItem = new ItemDTO("razor", 1, 59, 0.25);
        ItemDTO secondItem = new ItemDTO("chicken", 2, 79, 0.12);
        int quantityOfItem = 1;
        ItemList itemList = new ItemList();
        itemList.logItem(firstItem, quantityOfItem);
        itemList.logItem(secondItem, quantityOfItem);
        Balance balance = new Balance();
        double result = balance.calculateTaxAmt(itemList.getItems());
        double expResult = Math.round((59*0.25 + 79*0.12)*100)/100.0;
        assertEquals(expResult, result, "Tax amount is not correct.");
    }
}