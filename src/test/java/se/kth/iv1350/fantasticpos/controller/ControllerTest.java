package se.kth.iv1350.fantasticpos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.NonexistentIdentifierException;
import se.kth.iv1350.fantasticpos.integration.Printer;
import se.kth.iv1350.fantasticpos.integration.RegistryCreator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private RegistryCreator creator;
    private Controller instance;
    private final int VALID_ITEM_ID = 1;
    private final int ONE_ITEM_QUANTITY = 1;
    private final int INVALID_ITEM_ID = -1;

    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @BeforeEach
    public void setup() {
        creator = new RegistryCreator();
        Printer printer = new Printer();
        instance = new Controller(creator, printer);
        instance.startSale();

        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        creator = null;
        instance = null;

        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testStartSale() {
        try {
            instance.scanItem(VALID_ITEM_ID, ONE_ITEM_QUANTITY);
        } catch (NullPointerException e) {
            fail("Failed to create a new sale.");
            e.printStackTrace();
        } catch (NonexistentIdentifierException | OperationFailedException e) {
            fail("Got exception.");
            e.printStackTrace();
        }
    }

    @Test
    void testScanValidItemID() {
        try {
            instance.scanItem(VALID_ITEM_ID, ONE_ITEM_QUANTITY);
            boolean totalPrice = instance.endSale() > 0;
            assertTrue(totalPrice, "Total Price is less than or equal to zero " +
                    "when scanning for a valid item id.");
        } catch(NonexistentIdentifierException | OperationFailedException exc) {
            fail("Got exception.");
            exc.printStackTrace();
        }
    }

    @Test
    void testScanInvalidItemID() {
        try {
            instance.scanItem(INVALID_ITEM_ID, ONE_ITEM_QUANTITY);
            fail("Managed to successfully scan an invalid itemID.");
        } catch (OperationFailedException exc) {
            fail("Got exception.");
            exc.printStackTrace();
        } catch (NonexistentIdentifierException exc) {
            assertTrue(exc.getMessage().contains("Unable to find"),
                    "Wrong exception message, does not contain the invalid itemID: "
                            + exc.getMessage());
        }
    }

    @Test
    void testEndSale() {
        try {
            instance.scanItem(VALID_ITEM_ID, ONE_ITEM_QUANTITY);
            boolean totalPrice = instance.endSale() > 0;
            assertTrue(totalPrice, "Wrong total price.");
        } catch (NonexistentIdentifierException | OperationFailedException exc) {
            fail("Got exception.");
            exc.printStackTrace();
        }
    }

    @Test
    void testPay() {
        try {
            instance.scanItem(VALID_ITEM_ID, ONE_ITEM_QUANTITY);
        } catch (NonexistentIdentifierException | OperationFailedException exc) {
            fail("Got exception.");
            exc.printStackTrace();
        }
        double paidAmt = 100;
        instance.pay(paidAmt);

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