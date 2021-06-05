package se.kth.iv1350.fantasticpos.integration;

/**
 * Contains information about one particular item.
 */
public final class ItemDTO {
    private String name;
    private int itemIdentifier;
    private double price;
    private double VATRate;

    /**
     * Creates a new instance representing a particular item.
     *  @param name The item name.
     * @param itemIdentifier Bar code numbers representing the specific item.
     * @param price The item's price.
     * @param VATRate The item's VATRate, can be 25 %, 12 % or 6 %.
     */
    public ItemDTO(String name, int itemIdentifier, double price, double VATRate) {
        this.name = name;
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.VATRate = VATRate;
    }

    /**
     * Two <code>ItemDTO</code> instances are equal if all fields are equal.
     *
     * @param otherObj The <code>ItemDTO</code> to compare with this
     *                 <code>ItemDTO</code>.
     * @return <code>true</code> if all fields in the specified
     *         <code>ItemDTO</code> are equal to corresponding fields in this
     *         <code>ItemDTO</code>, <code>false</code> if they are not.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }

        final ItemDTO other = (ItemDTO) otherObj;

        if (!name.equals(other.name)) {
            return false;
        }
        if (itemIdentifier != other.itemIdentifier) {
            return false;
        }
        if (price != other.price) {
            return false;
        }
        if (VATRate != other.VATRate) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String string = "name: " + name + ", " +
                "itemIdentifier: " + itemIdentifier + ", " +
                "price: " + price + ", " +
                "VATRate: " + VATRate;
        return string;
    }

    /**
     * Get the value of name
     *
     * @return the value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of itemIdentifier.
     *
     * @return the value of itemIdentifier.
     */
    public int getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Get the value of price.
     *
     * @return the value of price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the value of VATRate.
     *
     * @return the value of VATRate.
     */
    public double getVATRate() {
        return VATRate;
    }
}
