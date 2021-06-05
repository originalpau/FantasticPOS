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

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        this.logger = new LogHandler();
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void runSampleExecution() {
        try {
            SaleInfoDTO saleInfo;
            final int ONE_ITEM = 1;
            final int INVALID_ITEM_ID = -1;
            final int FIRST_ITEM_ID = 3;
            final int SECOND_ITEM_ID = 2;
            final int SAME_SECOND_ITEM_ID = 2;
            final int DATABASE_FAILURE_ID = 1337;

            contr.startSale();
            System.out.println("\nA NEW SALE HAS BEEN STARTED.\n");

            System.out.println("------------------------ Start of scanning Procedure ------------" +
                    "-------------------\n");

            saleInfo = contr.scanItem(FIRST_ITEM_ID, ONE_ITEM);
            System.out.println(saleInfo);

            saleInfo = contr.scanItem(SECOND_ITEM_ID, ONE_ITEM);
            System.out.println(saleInfo);

            saleInfo = contr.scanItem(SAME_SECOND_ITEM_ID, ONE_ITEM);
            System.out.println(saleInfo);

            try {
                System.out.println("Trying to scan an invalid identifier, should generate an error.");
                saleInfo = contr.scanItem(INVALID_ITEM_ID, ONE_ITEM);
                System.out.println(saleInfo);
                errorMsgHandler.showErrorMsg("Managed to scan a nonexistent identifier.");
            } catch (NonexistentIdentifierException exc) {
                errorMsgHandler.showErrorMsg("Correctly failed to scan an nonexistent identifier.");
            } catch (OperationFailedException exc) {
                writeToLogAndUI("Wrong exception was thrown.", exc);
            }

            try {
                System.out.println("Trying to scan when database can not be called.");
                saleInfo = contr.scanItem(DATABASE_FAILURE_ID, ONE_ITEM);
                System.out.println(saleInfo);
                errorMsgHandler.showErrorMsg("Managed to connect to the database when expecting failure.");
            } catch (OperationFailedException exc) {
                errorMsgHandler.showErrorMsg("Correctly failed to call the database.");
            } catch (NonexistentIdentifierException exc) {
                writeToLogAndUI("Wrong exception was thrown.", exc);
            }


            System.out.println("------------------------ End of scanning procedure --------------" +
                    "-------------------\n");

            double totalSum = contr.endSale();
            System.out.println("END SALE.\nTOTAL SUM INCL. VAT: " + totalSum + " SEK\n");

            double paidAmount = 300.00;
            System.out.println("---------------- Receipt follows --------------");
            contr.pay(paidAmount);
            System.out.println("--------------- End of receipt ----------------");

        } catch (NonexistentIdentifierException exc) {
            errorMsgHandler.showErrorMsg(exc.getInvalidIdentifier() + "is nonexistent.");
        } catch (Exception exc) {
            writeToLogAndUI("Failed to scan, please try again.", exc);
        }
    }

    private void writeToLogAndUI(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }
}
