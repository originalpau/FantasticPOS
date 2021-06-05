package se.kth.iv1350.fantasticpos.integration;

/**
 * This class is responsible for instantiating all registries.
 */

public class RegistryCreator {
    private InventoryRegistry inventoryRegistry;
    private AccountingRegistry accountingRegistry;

    /**
     * Creates a new instance of RegistryCreator.
     * A new instance of InventoryRegistry and AccountingRegistry is also created.
     */
    public RegistryCreator() {
        inventoryRegistry =  new InventoryRegistry();
        accountingRegistry = new AccountingRegistry();
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
