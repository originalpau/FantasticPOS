package se.kth.iv1350.fantasticpos.model;

import se.kth.iv1350.fantasticpos.integration.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of scanned items in current sale.
 */
class ItemList {
    private final List<ItemsInCart> items = new ArrayList<>();

    /**
     * Get the value of arrayList Items.
     * @return a copy of the value in arrayList Items.
     */
     List<ItemsInCart> getItems() {
        List<ItemsInCart> copyOfitems = new ArrayList<>(items);
        return copyOfitems;
    }

    /**
     * Registers item in the item list. If the specified item has been registered before,
     * the quantity of that item increases.
     *
     * @param foundItem Information about the found item in inventoryRegistry from the scanned bar code.
     * @param quantityOfItem Quantity of the scanned item.
     */
     void logItem(ItemDTO foundItem, int quantityOfItem) {
        boolean itemAdded = false;

        if (items.isEmpty()) addItemToCart(foundItem, quantityOfItem);
        else {
            for (ItemsInCart item : items) {
                if (itemIsInCurrentSale(item, foundItem)) {
                    increaseQuantityOfItem(item, quantityOfItem);
                    itemAdded = true;
                }
            }
            if (!itemAdded) {
                addItemToCart(foundItem, quantityOfItem);
            }
        }
    }

    private boolean itemIsInCurrentSale (ItemsInCart item, ItemDTO foundItem) {
        if (item.getItemIdentifier() == foundItem.getItemIdentifier() &&
                item.getName().equals(foundItem.getName()) &&
                item.getPrice() == foundItem.getPrice() &&
                item.getVATRate() == foundItem.getVATRate()) {
            return true;
        }
        else {
            return false;
        }
    }

    private void increaseQuantityOfItem (ItemsInCart item, int quantityOfItem) {
        int newItemQuantity = quantityOfItem + item.getItemQuantity();
        item.setItemQuantity(newItemQuantity);
    }

    private void addItemToCart (ItemDTO foundItem, int quantityOfItem) {
        ItemsInCart addItemToCart = new ItemsInCart(foundItem, quantityOfItem);
        items.add(addItemToCart);
    }
}

