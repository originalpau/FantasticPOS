package se.kth.iv1350.fantasticpos.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.fantasticpos.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {

    @Test
    void testLogFirstItem() {
        ItemList instance = new ItemList();
        ItemDTO foundItem = new ItemDTO("banana", 100, 5, 0.12);
        int quantityOfItem = 1;

        instance.logItem(foundItem, quantityOfItem);
        int expResult = 1;
        int result = instance.getItems().size();
        assertEquals(expResult, result, "First item is not added to ItemList");
    }

    @Test
    void testLogSecondItem() {
        ItemList instance = new ItemList();
        ItemDTO firstItem = new ItemDTO("banana", 100, 5, 0.12);
        int quantityOfItem = 1;
        instance.logItem(firstItem, quantityOfItem);

        ItemDTO secondItem = new ItemDTO("razor", 1, 59, 0.25);
        instance.logItem(secondItem, quantityOfItem);

        int expResult = 2;
        int result = instance.getItems().size();
        assertEquals(expResult, result, "Second item is not added to ItemList");
    }

    @Test
    void testLogSameItem() {
        ItemList instance = new ItemList();
        int quantityOfItem = 1;
        ItemDTO foundItem = new ItemDTO("banana", 100, 5, 0.12);
        instance.logItem(foundItem, quantityOfItem);
        ItemDTO sameItem = new ItemDTO("banana", 100, 5, 0.12);
        instance.logItem(sameItem, quantityOfItem);
        int expResult = 1;
        int result = instance.getItems().size();
        assertEquals(expResult, result,"Same item are registered as separate item objects.");
    }

}