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
        return receiptCleanFormat();
    }

    private String createTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime timeOfSale = LocalDateTime.now();
        return dtf.format(timeOfSale);
    }

    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }

    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }

    private String receiptCleanFormat() {
        StringBuilder builder = new StringBuilder();

        builder.append("\nReceipt follows...");
        endSection(builder);

        builder.append(createNewReceiptLine());
        endSection(builder);

        String storeinfo = createStoreInfo();
        builder.append(storeinfo);

        builder.append(createDottedLine());
        endSection(builder);

        builder.append(createItemsTitle());
        endSection(builder);

        builder.append(createDottedLine());
        endSection(builder);

        builder.append(createItemsInCart());
        endSection(builder);

        builder.append(createTotalPriceInfo());
        endSection(builder);

        builder.append(createDottedLine());
        endSection(builder);

        builder.append(createAmountPaidInfo());
        endSection(builder);

        appendLine(builder, createChangeInfo());
        endSection(builder);

        builder.append(createTaxInfo());
        endSection(builder);

        builder.append(createBarCode());
        endSection(builder);

        builder.append(createNewReceiptLine());
        endSection(builder);

        return builder.toString();
    }

    private String receiptShortFormat() {
        StringBuilder builder = new StringBuilder();
        builder.append("================ Receipt follows ==============");
        endSection(builder);

        String storeInfo = createStoreInfo();
        builder.append(storeInfo);

        builder.append(createItemsTitle());
        endSection(builder);

        builder.append(String.format("%-15s %10s %20s", "----", "--------", "----------"));
        endSection(builder);

        builder.append(createItemsInCart());
        endSection(builder);

        appendLine(builder, createTotalPriceInfo());
        endSection(builder);

        builder.append(createAmountPaidInfo());
        endSection(builder);

        appendLine(builder, createChangeInfo());
        endSection(builder);

        builder.append(createTaxInfo());
        endSection(builder);

        builder.append("=============== End of receipt ================");
        endSection(builder);

        return builder.toString();
    }

    private String createStoreInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n             Paulina Huang's Store");
        endSection(builder);

        builder.append("        KTH Royal Institute of Technology");
        endSection(builder);

        appendLine(builder, "              " + createTime() + "    ");
        endSection(builder);

        return builder.toString();
    }

    private String createBarCode() {
        return "            || |||||| ||| |||||||| |\n" +
               "            || |||||| ||| |||||||| |\n";
    }

    private String createNewReceiptLine() {
        return "===============================================";
    }

    private String createDottedLine() {
        return "...............................................";
    }

    private String createItemsTitle() {
        return String.format("%-15s %10s %20s", "Item", "Quantity", "Price(SEK)");
    }

    private String createItemsInCart() {
        StringBuilder builder = new StringBuilder();
        for (ItemsInCart item : items) {
            builder.append(String.format("%-15s %10s %20s", item.getName(), item.getItemQuantity(),
                    Math.round(item.getPrice())));
            endSection(builder);
        }

        return builder.toString();
    }

    private String createTotalPriceInfo () {
        return String.format("%-15s %31s", "Total", currentSale.getTotalPrice());
    }

    private String createAmountPaidInfo() {
        return String.format("%-15s %31s", "Cash", currentSale.getPaidAmt());
    }

    private String createChangeInfo() {
        return String.format("%-15s %31s", "Change", currentSale.getChange());
    }

    private String createTaxInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tax/VAT included in the above total");
        endSection(builder);

        builder.append(String.format("%-15s %31s", "VAT", currentSale.getTaxAmt()));
        endSection(builder);

        return builder.toString();
    }
}