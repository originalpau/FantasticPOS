package se.kth.iv1350.fantasticpos.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistryCreatorTest {
    @Test
    public void testCreateInventoryRegistry() {
        RegistryCreator instance = new RegistryCreator();
        InventoryRegistry result = instance.getInventoryRegistry();
        assertTrue(result instanceof InventoryRegistry, "RegistryCreator did not create InventoryRegistry.");
    }

    @Test
    public void testCreateAccountingRegistry() {
        RegistryCreator instance = new RegistryCreator();
        AccountingRegistry result = instance.getAccountingRegistry();
        assertTrue(result instanceof AccountingRegistry,"RegistryCreator did not create AccountingRegistry.");
    }
}
