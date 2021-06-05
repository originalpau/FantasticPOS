package se.kth.iv1350.fantasticpos.model;

import java.util.List;

/**
 * Represents a Balance Calculator, doing all mathematical calculations in Sale.
 */
class Balance {
    /**
     * Calculates totalPrice of item objects in ArrayList items.
     *
     * @param items ArrayList of items in CurrentSale.
     * @return the value of <code>totalPrice</code>.
     */
     double calculateRunningTotal(List<ItemsInCart> items) {
        double totalPrice = 0.0;
        for (ItemsInCart item : items) {
            totalPrice += (item.getPrice() + item.getPrice() * item.getVATRate()) * item.getItemQuantity();
        }
        totalPrice = Math.round(totalPrice * 100)/100.0;
        return totalPrice;
    }

    /**
     * Calculates change to give back to the customer.
     *
     * @param paidAmount Amount paid by the customer.
     * @return Change to give back to the customer.
     */
     double calculateChange(double paidAmount, double totalPrice) {
        double change = Math.round(paidAmount - totalPrice);
        change = Math.round(change * 100) /100.0;
        return change;
    }

    /**
     * Calculate VAT for the entire sale.
     * @param items ArrayList of items in CurrentSale.
     * @return the value of <code>taxAmt</code>.
     */
     double calculateTaxAmt(List<ItemsInCart> items) {
        double taxAmt = 0;
        for (ItemsInCart item : items) {
            taxAmt += item.getPrice() * item.getVATRate() * item.getItemQuantity();
        }
        taxAmt = Math.round(taxAmt * 100)/100.0;
        return taxAmt;
    }
}
