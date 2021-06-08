package se.kth.iv1350.fantasticpos.model;

import java.io.IOException;

/**
 * A listener interface for receiving notifications about the sale. The class that is interested
 * in such notifications implements this interface, and the object created with that class is
 * registered with
 * {@link se.kth.iv1350.fantasticpos.controller.Controller#addSaleObserver(SaleObserver)}.
 * When a sale payment has been registered, that object's {@link #newSale(double payment)} method is invoked.
 */
public interface SaleObserver {

    /**
     * Invoked when a sale has been paid.
     * Adds the <code>payment</code> to revenue and the totalRevenue is printed to the log.
     *
     * @param payment The amount to pay the sale.
     */
    void newSale(double payment);
}
