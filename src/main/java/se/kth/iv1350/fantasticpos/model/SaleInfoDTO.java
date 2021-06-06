package se.kth.iv1350.fantasticpos.model;

import se.kth.iv1350.fantasticpos.integration.ItemDTO;

/**
 * Contains information about the latest registered item and updated running total.
 */
public class SaleInfoDTO {
    private ItemDTO latestRegisteredItem;
    private double runningTotal;

    /**
     * Creates a new instance of sale information.
     *
     * @param latestRegisteredItem The latest registered item in sale.
     * @param runningTotal The current running total.
     */
    public SaleInfoDTO(ItemDTO latestRegisteredItem, double runningTotal) {
        this.latestRegisteredItem = latestRegisteredItem;
        this.runningTotal = runningTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Scanning item...\n");
        sb.append(this.latestRegisteredItem + "\n");
        sb.append("Running total: " + this.runningTotal+" SEK\n");
        return sb.toString();
    }

    /**
     * Get the value of latest registered item.
     * @return the value of latest registered item.
     */
    public ItemDTO getLatestRegisteredItem() {
        return this.latestRegisteredItem;
    }

    /**
     * Get the value of running total.
     * @return the value of running total.
     */
    public double getRunningTotal() {
        return this.runningTotal;
    }
}
