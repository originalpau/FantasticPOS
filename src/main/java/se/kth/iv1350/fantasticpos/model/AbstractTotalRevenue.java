package se.kth.iv1350.fantasticpos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows the total revenue.
 */
public abstract class AbstractTotalRevenue implements SaleObserver {
    //private final List<Double> revenue;
    private Double totalRevenue = 0.0;

    /**
     * Creates a new instance and creates a new ArrayList.
     */
    protected AbstractTotalRevenue() {
        //revenue = new ArrayList<>();
    }

    /**
     * Adds the <code>payment</code> to revenue.
     *
     * @param payment The amount to pay the sale.
     */
    @Override
    public void newSale(double payment) {
        calculateTotalRevenue(payment);
        showTotalRevenue();
    }

    private void calculateTotalRevenue(double payment)  {
        /*
        revenue.add(payment);
        for (Double income : revenue) {
            this.totalRevenue = income + this.totalRevenue;
        }
         */
        this.totalRevenue += payment;
    }

    private void showTotalRevenue() {
        try {
            doShowTotalRevenue(totalRevenue);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalRevenue(Double totalRevenue) throws Exception;

    protected abstract void handleErrors(Exception e);
}
