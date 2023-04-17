package Entities;

/*
 * FreshProduce class represents all the fruits and vegetables.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

import java.util.Date;

public class FreshProduce extends PerishableItem {
    private float pricePerPound;

    public FreshProduce(int itemNo, String name, String brand, float price, int quantity, int maxQuantity, Asile asile, Supplier supplier, String bestBefore, float pricePerPound) {
        super(itemNo, name, brand, price, quantity, maxQuantity, asile, supplier, bestBefore);
        this.pricePerPound = pricePerPound;
    }

    public float getPricePerPound() {
        return pricePerPound;
    }

    public void setPricePerPound(float pricePerPound) {
        this.pricePerPound = pricePerPound;
    }
}
