package entities;

import java.time.LocalDate;

/*
 * FrozenItem class represent the frozen foods.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public final class FrozenItem extends PerishableItem {
    private float minTemp;

    public FrozenItem(int itemNo, String name, String brand, float price, int quantity, int maxQuantity, Asile freezer, Supplier supplier, LocalDate bestBefore, float minTemp) {
        super(itemNo, name, brand, price, quantity, maxQuantity, freezer, supplier, bestBefore);
        this.minTemp = minTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public String findMyItem() {
        return itemNo + " " + itemName + " is in freezer section" + "requires cold packaging. Min Temp:" + minTemp;
    }
}
