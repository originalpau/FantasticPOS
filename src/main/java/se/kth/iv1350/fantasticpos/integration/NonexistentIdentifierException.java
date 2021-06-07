package se.kth.iv1350.fantasticpos.integration;

/**
 * Thrown when the ItemID does not exist in the catalog.
 */
public class NonexistentIdentifierException extends Exception {
    /**
     * Creates a new instance with a message specifying for which identifier for which the search failed.
     * @param invalidIdentifier The identifier that could not be found.
     */
    public NonexistentIdentifierException(int invalidIdentifier) {
        super("Unable to find " + invalidIdentifier + " because it does not exist in the inventory catalog.");
    }
}
