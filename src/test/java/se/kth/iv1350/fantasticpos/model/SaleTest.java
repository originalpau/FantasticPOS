package se.kth.iv1350.fantasticpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.ItemDTO;
import se.kth.iv1350.fantasticpos.integration.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale instance;
    private ItemDTO foundItem;
    private final int ONE_ITEM_QUANTITY = 1;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @BeforeEach
    void setup() {
        instance = new Sale();
        foundItem = new ItemDTO("banana", 100, 5, 0.12);

        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void teardown() {
        instance = null;
        foundItem = null;

        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    void registerFirstItem() {
        instance.registerItem(foundItem, ONE_ITEM_QUANTITY);

        int expResult = 1;
        int result = instance.getItemList().getItems().size();
        assertEquals(expResult, result, "The first item did not get registered.");
    }

    @Test
    void registerSecondItem() {
        instance.registerItem(foundItem, ONE_ITEM_QUANTITY);

        ItemDTO secondItem = new ItemDTO("razor", 1, 59, 0.25);
        instance.registerItem(secondItem, ONE_ITEM_QUANTITY);

        int expResult = 2;
        int result = instance.getItemList().getItems().size();
        assertEquals(expResult, result, "Second item is not added to ItemList");
    }

    @Test
    void registerSameItem() {
        instance.registerItem(foundItem, ONE_ITEM_QUANTITY);

        ItemDTO sameItem = foundItem;
        instance.registerItem(sameItem, ONE_ITEM_QUANTITY);

        int expResult = 1;
        int result = instance.getItemList().getItems().size();
        assertEquals(expResult, result, "Same item are registered two times.");
    }

    @Test
    void testGetTotalPrice() {
        ItemDTO firstItem = new ItemDTO("razor", 1, 59, 0.25);
        ItemDTO secondItem = new ItemDTO("chicken", 2, 79, 0.12);
        ItemDTO thirdItem =  new ItemDTO("chicken", 2, 79, 0.12);

        instance.registerItem(firstItem, ONE_ITEM_QUANTITY);
        instance.registerItem(secondItem, ONE_ITEM_QUANTITY);
        instance.registerItem(thirdItem, ONE_ITEM_QUANTITY);

        double expResult = ((59 + 59*0.25) * 1) + ((79 + 79*0.12) * 2);
        double result = instance.getTotalPrice();

        assertEquals(expResult, result, "Total Price is not calculated correctly.");
    }

    @Test
    void testGetChange() {
        ItemDTO firstItem = new ItemDTO("razor", 1, 59, 0.25);
        ItemDTO secondItem = new ItemDTO("chicken", 2, 79, 0.12);

        instance.registerItem(firstItem, ONE_ITEM_QUANTITY);
        instance.registerItem(secondItem, ONE_ITEM_QUANTITY);
        double paidAmt = 500;
        instance.pay(paidAmt);
        double result = instance.getChange();
        double expTotalPrice = (59 + 59*0.25) + (79 + 79*0.12);
        double expResult = Math.round(paidAmt - expTotalPrice);
        assertEquals(expResult, result, "Change is not calculated correctly.");
    }

    @Test
    void testPay() {
        double paidAmt = 10;
        instance.registerItem(foundItem, ONE_ITEM_QUANTITY);
        instance.pay(paidAmt);
        double expTaxAmt = foundItem.getVATRate() * foundItem.getPrice();
        expTaxAmt = Math.round(expTaxAmt * 100)/100.0;
        double actualTaxAmt = instance.getTaxAmt();
        assertEquals(expTaxAmt, actualTaxAmt,"Wrong tax amount.");
    }

    @Test
    void testPrintReceipt() {
        double paidAmt = 10;
        instance.registerItem(foundItem, ONE_ITEM_QUANTITY);
        instance.pay(paidAmt);
        instance.printReceipt(new Printer());

        String expResultTax = "VAT";
        String expResultChange = "Change";
        LocalDateTime saleTime = LocalDateTime.now();

        String result = outContent.toString();

        assertTrue(result.contains(expResultChange), "Wrong change.");
        assertTrue(result.contains(expResultTax),"Wrong VAT.");
        assertTrue(result.contains(Integer.toString(saleTime.getYear())), "Wrong rental year.");
        assertTrue(result.contains(Integer.toString(saleTime.getYear())), "Wrong rental year.");
        assertTrue(result.contains(Integer.toString(saleTime.getMonthValue())), "Wrong rental month.");
        assertTrue(result.contains(Integer.toString(saleTime.getDayOfMonth())), "Wrong rental day.");
        assertTrue(result.contains(Integer.toString(saleTime.getHour())), "Wrong rental hour.");
        assertTrue(result.contains(Integer.toString(saleTime.getMinute())), "Wrong rental minute.");
    }
}