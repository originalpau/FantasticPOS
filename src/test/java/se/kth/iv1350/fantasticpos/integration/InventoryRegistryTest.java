package se.kth.iv1350.fantasticpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRegistryTest {
    private RegistryCreator creator;
    private InventoryRegistry instance;
    private final int BANANA_ID = 100;
    private final int INVALID_ID = -1;
    private final int DATABASE_FAILURE = 1337;

    @BeforeEach
    public void setUp() {
        this.creator = new RegistryCreator();
        this.instance = creator.getInventoryRegistry();
    }

    @AfterEach
    public void tearDown() {
        creator = null;
        instance = null;
    }

    @Test
    void testFindItem() throws NonexistentIdentifierException {
        ItemDTO expResult = new ItemDTO("Banana", 100, 5, 0.12);
        ItemDTO result = instance.findItem(BANANA_ID);
        assertEquals(expResult, result, "Available item was not found");
    }

    @Test
    void testFindInvalidItemID() {
        try {
            instance.findItem(INVALID_ID);
            fail("Nonexistent item was found.");
        } catch (NonexistentIdentifierException exc){
            assertTrue(exc.getMessage().contains("Unable to find"),
                    "Wrong exception message, does not contain specified identifier: " + exc.
                            getMessage());
        }
    }

    @Test
    void testDatabaseFailure() {
        try {
            instance.findItem(DATABASE_FAILURE);
            fail("Could connect to unavailable database.");
        } catch(NonexistentIdentifierException exc) {
            fail("Got exception.");
            exc.printStackTrace();
        } catch (InventoryRegistryException exc) {
            assertTrue(exc.getMessage().contains("connect"), "Wrong exception message, " +
                    "does not contain database connection failure info." + exc.getMessage());
        }
    }
}