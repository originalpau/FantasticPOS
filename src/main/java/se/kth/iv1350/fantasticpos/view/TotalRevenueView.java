package se.kth.iv1350.fantasticpos.view;

import se.kth.iv1350.fantasticpos.model.SaleObserver;

/**
 * Prints the total revenue on the console. Informs about revenue to the user.
 */
class TotalRevenueView implements SaleObserver {
    private double totalRevenue = 0;

    /**
     * Adds the current payment to totalRevenue. Prints the total revenue to <code>System.out</code>.
     * @param payment The amount to pay the sale.
     */
    @Override
    public void newSale(double payment) {
        this.totalRevenue = payment + this.totalRevenue;
        printCurrentState();
    }

    private void printCurrentState() {
        System.out.println(" ___________________________");
        System.out.println("| Cash Register Update      |");
        System.out.println("| TOTAL REVENUE: " + Math.round(this.totalRevenue * 100)/100.0 +
                " SEK |");
        System.out.println("|___________________________|");
    }
}
