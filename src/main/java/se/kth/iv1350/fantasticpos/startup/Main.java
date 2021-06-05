package se.kth.iv1350.fantasticpos.startup;

import se.kth.iv1350.fantasticpos.controller.Controller;
import se.kth.iv1350.fantasticpos.integration.Printer;
import se.kth.iv1350.fantasticpos.integration.RegistryCreator;
import se.kth.iv1350.fantasticpos.view.View;

import java.io.IOException;

/**
 * Contains the <code>main</code> method. Performs all startup of the
 * application.
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        try {
            RegistryCreator creator = new RegistryCreator();
            Printer printer = new Printer();
            Controller contr = new Controller(creator, printer);
            new View(contr).runSampleExecution();
        } catch (IOException exc) {
            System.out.println("Unable to start the application");
            exc.printStackTrace();
        }
    }
}

