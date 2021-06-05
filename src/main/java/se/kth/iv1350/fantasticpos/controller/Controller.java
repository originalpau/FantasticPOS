package se.kth.iv1350.fantasticpos.controller;

import se.kth.iv1350.fantasticpos.integration.*;
import se.kth.iv1350.fantasticpos.model.CashRegister;
import se.kth.iv1350.fantasticpos.model.Sale;
import se.kth.iv1350.fantasticpos.model.SaleInfoDTO;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Printer printer;
    private Sale currentSale;
    private InventoryRegistry inventoryRegistry;
    private AccountingRegistry accountingRegistry;
    private CashRegister cashRegister;

    /**
     * Creates a new instance.
     *
     * @param creator Used to get all classes that handle database calls.
     * @param printer Interface to printer.
     */
    public Controller(RegistryCreator creator, Printer printer) {
        this.inventoryRegistry = creator.getInventoryRegistry();
        this.accountingRegistry = creator.getAccountingRegistry();
        this.printer = printer;
        this.cashRegister = new CashRegister();
    }

    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale() {
        this.currentSale = new Sale();
    }

    /**
     * Search for matching item with bar code.
     *
     * @param itemIdentifier The item's bar code, represented by numbers.
     * @param quantityOfItem The quantity of scanned item.
     * @return sale information with latest scanned item and updated running total.
     * @throws OperationFailedException if unable to scan an item for any other reason than it being invalid.
     * @throws NonexistentIdentifierException if the specified identifier does not exist in the inventory catalog.
     *
     */
    public SaleInfoDTO scanItem(int itemIdentifier, int quantityOfItem) throws NonexistentIdentifierException,
            OperationFailedException {
        try {
            ItemDTO foundItem = inventoryRegistry.findItem(itemIdentifier);
            SaleInfoDTO saleinfo = currentSale.registerItem(foundItem, quantityOfItem);
            return saleinfo;
        } catch(InventoryRegistryException exc) {
            throw new OperationFailedException("Could not scan the identifier.", exc);
        }
    }

    /**
     * Ends the current sale.
     *
     * @return the total price of the current sale.
     */
    public double endSale() {
        double totalPrice = currentSale.getTotalPrice();
        return totalPrice;
    }

    /**
     * Pays the sale with the given paidAmt.
     * Updates the balance of the cash register where the payment was performed.
     * Calculates change.
     * Prints the receipt.
     * Updates accountingRegistry and inventoryRegistry with total sale information.
     *
     * @param paidAmt The paid amount.
     */
    public void pay(double paidAmt) {
        currentSale.pay(paidAmt);
        cashRegister.addPayment(currentSale.getTotalPrice());
        currentSale.printReceipt(printer);
        accountingRegistry.sendAccountingRegistry(currentSale);
        inventoryRegistry.updateInventory(currentSale);
    }
}
