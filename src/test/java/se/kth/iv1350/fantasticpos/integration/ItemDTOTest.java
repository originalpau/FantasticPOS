package se.kth.iv1350.fantasticpos.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {

    @Test
    public void testEquals() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        ItemDTO equalInstance = new ItemDTO(name, itemIdentifier, price, VATRate);
        boolean expResult = true;
        boolean result = instance.equals(equalInstance);
        assertEquals(expResult, result, "ItemDTO instances with same states are not equal.");
    }

    @Test
    public void testNotEqualsName() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        ItemDTO notEqualInstance = new ItemDTO("wrong", itemIdentifier, price, VATRate);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different name are equal.");
    }

    @Test
    public void testNotEqualsItemID() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        ItemDTO notEqualInstance = new ItemDTO(name, 456, price, VATRate);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different itemID are equal.");
    }

    @Test
    public void testNotEqualsPrice() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        ItemDTO notEqualInstance = new ItemDTO(name, itemIdentifier, 10.0, VATRate);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different price are equal.");
    }

    @Test
    public void testNotEqualsVATRate() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        ItemDTO notEqualInstance = new ItemDTO(name, itemIdentifier, price, 0.25);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different VATRate are equal.");
    }

    @Test
    public void testNotEqualsNullParam() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        ItemDTO notEqualInstance = new ItemDTO(null, itemIdentifier, price, VATRate);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different name are equal.");
    }

    @Test
    public void testNotEqualsEmptyStringParam() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        ItemDTO notEqualInstance = new ItemDTO("", itemIdentifier, price, VATRate);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different name are equal.");
    }

    @Test
    public void testNotEqualToJavaLangObj() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        Object notEqualInstance = new Object();
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instance equal to instance of java.lang.Object.");
    }

    @Test
    public void testNotEqualToNull() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        Object notEqualInstance = null;
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instance equal to null.");
    }

    @Test
    public void testToString() {
        String name = "banana";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        String expResult = "name: " + name + ", itemIdentifier: " + itemIdentifier +
                ", price: " + price + ", VATRate: " + VATRate;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void testToStringNullParam() {
        String name = null;
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        String expResult = "name: " + name + ", itemIdentifier: " + itemIdentifier +
                ", price: " + price + ", VATRate: " + VATRate;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void testToStringEmptyStringParam() {
        String name = "";
        int itemIdentifier = 123;
        double price = 5.0;
        double VATRate = 0.12;
        ItemDTO instance = new ItemDTO(name, itemIdentifier, price, VATRate);
        String expResult = "name: " + name + ", itemIdentifier: " + itemIdentifier +
                ", price: " + price + ", VATRate: " + VATRate;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }
}