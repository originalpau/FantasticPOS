package se.kth.iv1350.fantasticpos.view;

import se.kth.iv1350.fantasticpos.model.AbstractTotalRevenue;

/**
 * Prints the total revenue on the console. Informs about revenue to the user.
 */
class TotalRevenueView extends AbstractTotalRevenue {

    /**
     * Prints the total revenue to <code>System.out</code>.
     */
    @Override
    protected void doShowTotalRevenue (Double totalRevenue) {
        System.out.println(" ___________________________");
        System.out.println("| Cash Register Update      |");
        System.out.println("| TOTAL REVENUE: " + Math.round(totalRevenue * 100)/100.0 +
                " SEK |");
        System.out.println("|___________________________|");
    }

    @Override
    protected void handleErrors(Exception e) {
        LoggerClient client = new LoggerClient();
        client.writeToConsoleAndFile("Failed to print total revenue", e);
    }
}
