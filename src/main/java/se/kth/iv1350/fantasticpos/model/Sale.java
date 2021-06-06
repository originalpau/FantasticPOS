package se.kth.iv1350.fantasticpos.model;

import se.kth.iv1350.fantasticpos.integration.ItemDTO;
import se.kth.iv1350.fantasticpos.integration.Printer;

import java.util.List;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {
    private ItemList itemList;
    private double totalPrice;
    private double change;
    private Balance balance;
    private double paidAmt;
    private double taxAmt;

    /**
     * Creates a new instance of Sale.
     * Creates an instance of Balance Calculator and ItemList.
     */
    public Sale() {
        itemList = new ItemList();
        balance = new Balance();
    }

    /**
     * Get the value of itemList.
     * @return the value of itemList.
     */
    public ItemList getItemList() {
        return itemList;
    }

    /**
     * The scanned item is registered in item list.
     * @param foundItem The found item from scanned bar code.
     * @param quantityOfItem The quantity of scanned item.
     * @return Updated SaleInfoDTO.
     */
    public SaleInfoDTO registerItem(ItemDTO foundItem, int quantityOfItem) {
        itemList.logItem(foundItem, quantityOfItem);
        updateTotalPrice();
        return createSaleInfo(foundItem);
    }

    private SaleInfoDTO createSaleInfo(ItemDTO foundItem) {
        return new SaleInfoDTO(foundItem, totalPrice);
    }

    private void updateTotalPrice() {
        totalPrice = balance.calculateRunningTotal(itemList.getItems());
    }

    /**
     * Get the value of totalPrice.
     * @return the value of totalPrice.
     */
    public double getTotalPrice() {
        double copyOfTotalPrice = totalPrice;
        return copyOfTotalPrice;
    }

    private void calculateChange(double paidAmt) {
        change = balance.calculateChange(paidAmt, totalPrice);
    }

    /**
     * Get the value of change.
     * @return the value of change.
     */
    double getChange() {
        double copyofChange = change;
        return copyofChange;
    }

    private void calculateTaxAmt() {
        taxAmt = balance.calculateTaxAmt(itemList.getItems());
    }

    /**
     * Get the value of taxAmt.
     * @return the value of taxAmt.
     */
    double getTaxAmt() {
        return taxAmt;
    }

    /**
     * This sale is paid using the specified paid amount.
     *
     * @param paidAmt The payment used to pay this sale.
     */
    public void pay(double paidAmt) {
        this.paidAmt = paidAmt;
        calculateChange(this.paidAmt);
        calculateTaxAmt();
    }

    /**
     * Get the value of paid amount.
     * @return the value of paidAmt.
     */
    double getPaidAmt() {
        return paidAmt;
    }

    /**
     * Prints a receipt for the current sale on the specified printer.
     *
     * @param printer The printer where the receipt is printed.
     */
    public void printReceipt(Printer printer) {
        Receipt receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
}
