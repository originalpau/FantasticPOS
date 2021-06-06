package se.kth.iv1350.fantasticpos.integration;

/**
 * A Singleton that is responsible for instantiating all registers.
 */

public class RegistryCreator {
    private InventoryRegistry inventoryRegistry;
    private AccountingRegistry accountingRegistry;

    /**
     * Creates a new instance of RegistryCreator.
     * A new instance of InventoryRegistry and AccountingRegistry is also created.
     */
     RegistryCreator() {
        inventoryRegistry =  InventoryRegistry.getInventoryRegistry();
        accountingRegistry = AccountingRegistry.getAccountingRegistry();
    }

    /**
     * Get the value of inventoryRegistry.
     *
     * @return the value of inventoryRegistry.
     */
    public InventoryRegistry getInventoryRegistry() {
        return inventoryRegistry;
    }

    /**
     * Get the value of accountingRegistry.
     *
     * @return the value of accountingRegistry.
     */
    public AccountingRegistry getAccountingRegistry() {
        return accountingRegistry;
    }
}
