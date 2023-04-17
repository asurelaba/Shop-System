package Entities;

import java.util.Date;

/*
 * Diary class represents the diary products.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Diary extends PerishableItem{
    private Refrigirator refrigirator;

    public Diary(int itemNo, String name, String brand, float price, int quantity, int maxQuantity, Asile asile, Supplier supplier, String bestBefore) {
        super(itemNo, name, brand, price, quantity, maxQuantity, asile, supplier, bestBefore);
    }

    public Refrigirator getRefrigirator() {
        return refrigirator;
    }

    public void setRefrigirator(Refrigirator refrigirator) {
        this.refrigirator = refrigirator;
    }
}
