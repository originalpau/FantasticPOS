package se.kth.iv1350.fantasticpos.model;

/**
 * Shows the total revenue.
 */
public abstract class AbstractTotalRevenue implements SaleObserver {
    private Double totalRevenue = 0.0;

    /**
     * Creates a new instance.
     */
    protected AbstractTotalRevenue() {
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
