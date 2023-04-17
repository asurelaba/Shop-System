package Entities;

import java.util.Date;

/*
 * Frozen class represent the frozen foods.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Frozen extends PerishableItem{
    private float minTemp;
    private Freezer freezer;

    public Frozen(int itemNo, String name, String brand, float price, int quantity,
                  int maxQuantity, Asile asile, Supplier supplier, String bestBefore,
                  float minTemp, Freezer freezer) {
        super(itemNo, name, brand, price, quantity, maxQuantity, asile, supplier, bestBefore);
        this.minTemp = minTemp;
        this.freezer = freezer;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public Freezer getFreezer() {
        return freezer;
    }

    public void setFreezer(Freezer freezer) {
        this.freezer = freezer;
    }
}
