package se.kth.iv1350.fantasticpos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents one receipt, which proves the payment of one sale.
 */
public class Receipt {
    private final Sale currentSale;
    private List<ItemsInCart> items;

    /**
     * Creates a new instance.
     *
     * @param currentSale The rental proved by this receipt.
     */
    public Receipt(Sale currentSale) {
        this.currentSale = currentSale;
        this.items = currentSale.getItemList().getItems();
    }

    /**
     * Creates a well-formatted string with the entire content of the receipt.
     *
     * @return The well-formatted receipt string.
     */
    public String createReceiptString() {
        StringBuilder builder = new StringBuilder();
        appendLine(builder, "Sale");
        endSection(builder);

        builder.append("Paulina Huang's Store at KTH");
        endSection(builder);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime timeOfSale = LocalDateTime.now();
        builder.append("Time of sale: ");
        appendLine(builder, dtf.format(timeOfSale));
        endSection(builder);

        builder.append(String.format("%-15s %10s %20s", "Item", "Quantity", "Price(SEK)"));
        endSection(builder);

        builder.append(String.format("%-15s %10s %20s", "----", "--------", "----------"));
        endSection(builder);

        for (ItemsInCart item : items) {
            builder.append(String.format("%-15s %10s %20s", item.getName(), item.getItemQuantity(),
                    Math.round(item.getPrice())));
            endSection(builder);
        }

        builder.append(String.format("%-15s %31s", "VAT", currentSale.getTaxAmt()));
        endSection(builder);

        builder.append(String.format("%47s", "----------"));
        endSection(builder);

        appendLine(builder, String.format("%-15s %31s", "Total", currentSale.getTotalPrice()));
        endSection(builder);

        builder.append(String.format("%-15s %31s", "Amount paid", currentSale.getPaidAmt()));
        endSection(builder);

        builder.append(String.format("%-15s %31s", "Change", currentSale.getChange()));
        endSection(builder);

        return builder.toString();
    }

    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }

    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }
}
