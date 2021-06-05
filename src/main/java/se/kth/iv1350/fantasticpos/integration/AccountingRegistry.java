package se.kth.iv1350.fantasticpos.integration;

import se.kth.iv1350.fantasticpos.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an Accounting Registry Handler, acting as a
 * middleware to the external data base Accounting Registry.
 */
public class AccountingRegistry {
    private List<Sale> sales = new ArrayList<>();

    /**
     * Saves the specified sale to be sent to the AccountingRegistry.
     * @param currentSale The sale that will be saved.
     */
    public void sendAccountingRegistry(Sale currentSale) {
        sales.add(currentSale);
    }
}
