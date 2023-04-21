package entities;

/*
 * PerishableItem represents the items that have expiry date.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class PerishableItem extends Item {
    protected String bestBefore;

    public PerishableItem(int itemNo, String name, String brand, float price, int quantity, int maxQuantity, Asile asile, Supplier supplier, String bestBefore) {
        super(itemNo, name, brand, price, quantity, maxQuantity, asile, supplier);
        this.bestBefore = bestBefore;
    }

    public PerishableItem(int itemNo, String name, String brand, int quantity, int maxQuantity, Asile asile, Supplier supplier, String bestBefore) {
        super(itemNo, name, brand, quantity, maxQuantity, asile, supplier);
        this.bestBefore = bestBefore;
    }

    public String getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(String bestBefore) {
        this.bestBefore = bestBefore;
    }

    public String findMyItem(){
        return itemNo + " " + itemName + "is in Asile " + asile + "bestBefore: " + bestBefore;
    }
}
