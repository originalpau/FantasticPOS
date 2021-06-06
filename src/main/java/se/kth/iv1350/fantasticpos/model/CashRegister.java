package se.kth.iv1350.fantasticpos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cash register. Holds all payments since program started.
 */

public class CashRegister {
    private List<SaleObserver> revenueObservers = new ArrayList<>();
    private double totalRevenue = 0;

    /**
     * Payment is added to totalRevenue.
     * @param totalPrice The amount paid for this sale.
     */
    public void addPayment(double totalPrice) {
        totalRevenue = totalRevenue + totalPrice;
        notifyObservers(totalPrice);
    }

    private void notifyObservers(double totalPrice) {
        for (SaleObserver obs : revenueObservers) {
            obs.newSale(totalPrice);
        }
    }

    /**
     * All the specified observers will be notified when this sale has been paid.
     *
     * @param observers The observers to notify.
     */
    public void addRevenueObservers(List<SaleObserver> observers) {
        revenueObservers.addAll(observers);
    }
}
