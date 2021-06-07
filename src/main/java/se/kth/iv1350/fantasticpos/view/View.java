package se.kth.iv1350.fantasticpos.view;

import se.kth.iv1350.fantasticpos.controller.Controller;
import se.kth.iv1350.fantasticpos.controller.OperationFailedException;
import se.kth.iv1350.fantasticpos.integration.NonexistentIdentifierException;
import se.kth.iv1350.fantasticpos.model.SaleInfoDTO;

import java.io.IOException;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    private LoggerClient logger;

    SaleInfoDTO saleInfo;
    final int ONE_ITEM_QUANTITY = 1;
    final int INVALID_ITEM_ID = -1;
    final int FIRST_ITEM_ID = 3;
    final int SECOND_ITEM_ID = 2;
    final int SAME_SECOND_ITEM_ID = 2;
    final int THIRD_ITEM_ID = 1;
    final int DATABASE_FAILURE_ID = 1337;

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        logger = new LoggerClient();
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void runSampleExecution() {
        firstSale();
        //secondSale();
    }

    private void firstSale() {
        try {
            contr.startSale();
            printNewSaleMessage();

            printNewScanningMessage();
            scan(FIRST_ITEM_ID, ONE_ITEM_QUANTITY);
            scan(SECOND_ITEM_ID, ONE_ITEM_QUANTITY);
            scan(SAME_SECOND_ITEM_ID, ONE_ITEM_QUANTITY);
            scanInvalidIdentifier();
            databaseOutOfOrder();
            printEndScanningMessage();

            receiveTotalPrice();

            contr.pay(300.0);

            printEndSaleMessage();
        } catch (Exception exc) {
            logger.writeToConsoleAndFile("Operation failed.", exc);
        }
    }

    private void secondSale() {
        try {
            contr.startSale();
            printNewSaleMessage();

            printNewScanningMessage();
            scan(THIRD_ITEM_ID, ONE_ITEM_QUANTITY);
            printEndScanningMessage();

            receiveTotalPrice();

            contr.pay(100.0);

            printEndSaleMessage();
        } catch (Exception exc) {
            logger.writeToConsoleAndFile("Operation failed.", exc);
        }
    }

    //Group of private methods that are called during a sale.

    private void printNewSaleMessage() {
        System.out.println("\n================ A NEW SALE HAS BEEN STARTED ================\n");
    }

    private void printEndSaleMessage() {
        System.out.println("\n================ THE SALE HAS BEEN COMPLETED ================\n");
    }

    private void printNewScanningMessage() {
        System.out.println("---------------- Start of scanning Procedure ----------------\n");
    }

    private void printEndScanningMessage() {
        System.out.println("---------------- End of scanning procedure ------------------\n");
    }

    private void scan(int identifier, int quantity) {
        try {
            saleInfo = contr.scanItem(identifier, quantity);
            System.out.println(saleInfo);
        } catch (NonexistentIdentifierException exc) {
            logger.printExceptionToConsole(exc);
        } catch (OperationFailedException exc) {
            logger.logExceptionToFile(exc);
            logger.printExceptionToConsole(exc);
        }
    }

    private void scanInvalidIdentifier() {
        try {
            System.out.println("Trying to scan an invalid identifier...");
            saleInfo = contr.scanItem(INVALID_ITEM_ID, ONE_ITEM_QUANTITY);
            System.out.println(saleInfo);
            logger.writeMessageToConsoleAndFile("Managed to scan a nonexistent identifier.");
        } catch (NonexistentIdentifierException exc) {
            logger.printMessageToConsole("Correctly failed to scan a nonexistent identifier.");
        } catch (OperationFailedException exc) {
            logger.writeToConsoleAndFile("Wrong exception was thrown.", exc);
        }
    }

    private void databaseOutOfOrder() {
        try {
            System.out.println("Trying to scan when database can not be called...");
            saleInfo = contr.scanItem(DATABASE_FAILURE_ID, ONE_ITEM_QUANTITY);
            System.out.println(saleInfo);
            logger.writeMessageToConsoleAndFile("Managed to connect to the database when expecting failure.");
        } catch (OperationFailedException exc) {
            logger.printMessageToConsole("Correctly failed to connect to database.");
        } catch (NonexistentIdentifierException exc) {
            logger.writeToConsoleAndFile("Wrong exception was thrown.", exc);
        }
    }

    private void receiveTotalPrice() {
        double totalSum = contr.getTotalPrice();
        System.out.println("End Sale\nTOTAL SUM: " + totalSum + " SEK\n");
    }
}
