package se.kth.iv1350.fantasticpos.model;

/**
 * Represents a cash register. Holds all payments since program started.
 */

public class CashRegister {
    private double totalRevenue = 0;

    public void addPayment(double totalPrice) {
        totalRevenue = totalRevenue + totalPrice;
    }
}
