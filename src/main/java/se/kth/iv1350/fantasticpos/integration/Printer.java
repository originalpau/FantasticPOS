package se.kth.iv1350.fantasticpos.integration;

import se.kth.iv1350.fantasticpos.model.Receipt;

/**
 * The interface to the printer, used for all printouts initiated by this
 * program.
 */
public class Printer {

    /**
     * Prints the specified receipt. This dummy implementation prints to
     * <code>System.out</code> instead of a printer.
     *
     * @param receipt
     */
    public void printReceipt(Receipt receipt) {
        System.out.println(receipt.createReceiptString());
    }
}
