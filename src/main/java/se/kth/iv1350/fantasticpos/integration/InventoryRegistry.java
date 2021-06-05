package se.kth.iv1350.fantasticpos.integration;

import se.kth.iv1350.fantasticpos.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all items in store that can be purchased. Acting as a
 * middleware to the external data base Inventory Registry.
 */
public class InventoryRegistry {
    private final int DATABASE_FAILURE = 1337;
    private List<ItemDTO> items = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    InventoryRegistry() {
        addItems();
    }

    /**
     * Search for items in itemInventory if there is a matching bar code.
     *
     * @param itemIdentifier The item identifier, bar code represented by numbers.
     * @return An ItemDTO with the matching bar code.
     * @throws InventoryRegistryException if the database call failed
     * @throws NonexistentIdentifierException if the item could not be found
     * with the specified identifier
     *
     */
    public ItemDTO findItem (int itemIdentifier) throws NonexistentIdentifierException {
        ItemDTO item = getItemByIdentifier(itemIdentifier);
        return new ItemDTO(item.getName(), item.getItemIdentifier(), item.getPrice(),
                item.getVATRate());
    }

    /**
     * Saves the specified sale to be sent to the InventoryRegistry.
     * @param currentSale The sale that will be saved.
     */
    public void updateInventory(Sale currentSale) {
        sales.add(currentSale);
    }

    private ItemDTO getItemByIdentifier(int itemIdentifier) throws NonexistentIdentifierException{
        if (itemIdentifier == DATABASE_FAILURE) {
            throw new InventoryRegistryException("Could not connect to database.");
        }
        for (ItemDTO item : items) {
            if (itemIdentifier == item.getItemIdentifier()) {
                return item;
            }
        }
        throw new NonexistentIdentifierException(itemIdentifier);
    }

    private void addItems() {
        items.add(new ItemDTO("Razor", 1, 32, 0.25));
        items.add(new ItemDTO("Chicken", 2, 64.29, 0.12));
        items.add(new ItemDTO("Newspaper", 3, 65, 0.06));
        items.add(new ItemDTO("Banana", 100, 5, 0.12));
    }
}
