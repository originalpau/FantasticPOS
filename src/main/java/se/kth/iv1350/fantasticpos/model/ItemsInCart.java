package se.kth.iv1350.fantasticpos.model;

import se.kth.iv1350.fantasticpos.integration.ItemDTO;

class ItemsInCart {
    private String name;
    private int itemIdentifier;
    private double price;
    private double VATRate;
    private int itemQuantity;


    ItemsInCart(ItemDTO foundItem, int itemQuantity) {
        this.name = foundItem.getName();
        this.itemIdentifier = foundItem.getItemIdentifier();
        this.price = foundItem.getPrice();
        this.VATRate = foundItem.getVATRate();
        this.itemQuantity = itemQuantity;
    }

    /**
     * Get the value of name.
     * @return the value of name.
     */
    String getName() {
        return name;
    }

    /**
     * Get the value of itemIdentifier.
     * @return the value of itemIdentifier.
     */
    int getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Get the value of price.
     * @return the value of price.
     */
     double getPrice() {
        double copyOfPrice = this.price;
        return copyOfPrice;
    }

    /**
     * Get the value of VATRate.
     * @return the value of VATRate.
     */
    double getVATRate() {
        double copyOfVATRate = this.VATRate;
        return copyOfVATRate;
    }

    /**
     * Get the value of itemQuantity.
     * @return the value of itemQuantity.
     */
    int getItemQuantity() {
        int copyOfItemQuantity = this.itemQuantity;
        return copyOfItemQuantity;
    }

    /**
     * Set the value of itemQuantity.
     * @param newItemQuantity The quantity of this specified item in the current sale.
     */
    void setItemQuantity(int newItemQuantity) {
        this.itemQuantity = newItemQuantity;
    }
}