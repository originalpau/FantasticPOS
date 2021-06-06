package se.kth.iv1350.fantasticpos.integration.filehandling;

import se.kth.iv1350.fantasticpos.model.SaleObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Prints the total revenue to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private static final String LOG_FILE_NAME = "revenue-log.txt";
    private PrintWriter logFile;
    private List<Double> revenue = new ArrayList<>();

    /**
     * Creates a new instance and also creates a new log file. An existing log file will be deleted.
     */
    public TotalRevenueFileOutput() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Adds the <code>payment</code> to revenue and the totalRevenue is printed to the log.
     *
     * @param payment The amount to pay the sale.
     */
    @Override
    public void newSale(double payment) {
        revenue.add(payment);
        String logMessage = createLogMessage(revenue);
        logCurrentState(logMessage);
    }

    private String createLogMessage(List<Double> revenue) {
        Double totalRevenue = 0.0;
        StringBuilder sb = new StringBuilder();
        sb.append("SALES REVENUE\n");
        for (Double income : revenue) {
            totalRevenue = income + totalRevenue;
            sb.append("Income: " + income.toString() + " SEK\n");
        }
        sb.append("\nTotal revenue: " + Math.round(totalRevenue * 100)/100.0 + " SEK\n");

        return sb.toString();
    }

    private void logCurrentState(String logMessage) {
        logFile.println(logMessage);
    }
}
