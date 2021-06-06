package se.kth.iv1350.fantasticpos.integration;

import se.kth.iv1350.fantasticpos.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * A Singleton that is an Accounting Registry Handler, acting as a
 * middleware to the external data base Accounting Registry.
 */
public class AccountingRegistry {
    private static final AccountingRegistry ACCOUNTING_REGISTRY = new AccountingRegistry();
    private List<Sale> sales = new ArrayList<>();

    private AccountingRegistry() {}

    public static AccountingRegistry getAccountingRegistry() {
        return ACCOUNTING_REGISTRY;
    }

    /**
     * Saves the specified sale to be sent to the AccountingRegistry.
     * @param currentSale The sale that will be saved.
     */
    public void sendAccountingRegistry(Sale currentSale) {
        sales.add(currentSale);
    }
}
