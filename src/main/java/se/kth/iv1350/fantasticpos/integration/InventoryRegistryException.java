package se.kth.iv1350.fantasticpos.integration;

/**
 * Thrown when something goes wrong while performing an operation in the database.
 */
public class InventoryRegistryException extends RuntimeException {

    /**
     * Creates a new instance representing the condition described in the specified message.
     * @param msg A message that describes what went wrong.
     */
    public InventoryRegistryException(String msg) {super(msg); }
}
