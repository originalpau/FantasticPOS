package se.kth.iv1350.fantasticpos.view;

import se.kth.iv1350.fantasticpos.controller.Controller;
import se.kth.iv1350.fantasticpos.controller.OperationFailedException;
import se.kth.iv1350.fantasticpos.integration.NonexistentIdentifierException;
import se.kth.iv1350.fantasticpos.model.SaleInfoDTO;
import se.kth.iv1350.fantasticpos.util.LogHandler;

import java.io.IOException;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger;

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
        this.logger = new LogHandler();
        contr.addSaleObserver(new TotalRevenueView());
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void runSampleExecution() {
        firstSale();
        //secondSale();
    }

    private void firstSale() {
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
    }

    private void secondSale() {
        contr.startSale();
        printNewSaleMessage();

        printNewScanningMessage();
        scan(THIRD_ITEM_ID, ONE_ITEM_QUANTITY);
        printEndScanningMessage();

        receiveTotalPrice();

        contr.pay(100.0);

        printEndSaleMessage();
    }

    private void writeToLogAndUI(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }

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
            errorMsgHandler.showErrorMsg(exc.getInvalidIdentifier() + "is nonexistent.");
        } catch (Exception exc) {
            writeToLogAndUI("Failed to scan, please try again.", exc);
        }
    }

    private void scanInvalidIdentifier() {
        try {
            System.out.println("Trying to scan an invalid identifier...");
            saleInfo = contr.scanItem(INVALID_ITEM_ID, ONE_ITEM_QUANTITY);
            System.out.println(saleInfo);
            errorMsgHandler.showErrorMsg("Managed to scan a nonexistent identifier.");
        } catch (NonexistentIdentifierException exc) {
            errorMsgHandler.showErrorMsg("Correctly failed to scan a nonexistent identifier.");
        } catch (OperationFailedException exc) {
            writeToLogAndUI("Wrong exception was thrown.", exc);
        }
    }

    private void databaseOutOfOrder() {
        try {
            System.out.println("Trying to scan when database can not be called...");
            saleInfo = contr.scanItem(DATABASE_FAILURE_ID, ONE_ITEM_QUANTITY);
            System.out.println(saleInfo);
            errorMsgHandler.showErrorMsg("Managed to connect to the database when expecting failure.");
        } catch (OperationFailedException exc) {
            errorMsgHandler.showErrorMsg("Correctly failed to call the database.");
        } catch (NonexistentIdentifierException exc) {
            writeToLogAndUI("Wrong exception was thrown.", exc);
        }
    }

    private void receiveTotalPrice() {
        double totalSum = contr.getTotalPrice();
        System.out.println("End Sale\nTOTAL SUM: " + totalSum + " SEK\n");
    }

}
