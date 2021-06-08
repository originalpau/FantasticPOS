package se.kth.iv1350.fantasticpos.integration.filehandling;

import se.kth.iv1350.fantasticpos.model.AbstractTotalRevenue;
import se.kth.iv1350.fantasticpos.util.LogHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints the total revenue to a file.
 */
public class TotalRevenueFileOutput extends AbstractTotalRevenue {
    private static final String LOG_FILE_NAME = "revenue-log.txt";
    private PrintWriter logFile;

    /**
     * Creates a new instance and also creates a new log file. An existing log file will be deleted.
     */
    public TotalRevenueFileOutput() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
        } catch (IOException ioe) {
            System.out.println("Failed to create a new log.");
            ioe.printStackTrace();
        }
    }

    @Override
    protected void doShowTotalRevenue(Double totalRevenue) {
        String revenue = "SALES REVENUE\n" +
                "Total revenue: " + Math.round(totalRevenue * 100) / 100.0 + " SEK\n";
        logFile.println(revenue);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Failed to log total revenue.");
        LogHandler.getLogHandler().logException(e);
    }
}
