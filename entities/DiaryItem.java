package entities;

/*
 * Diary class represents the diary products.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class DiaryItem extends PerishableItem {

    public DiaryItem(int itemNo, String name, String brand, float price, int quantity, int maxQuantity, Asile asile, Supplier supplier, String bestBefore) {
        super(itemNo, name, brand, price, quantity, maxQuantity, asile, supplier, bestBefore);
    }

    public String findMyItem() {
        return itemNo + " " + itemName + " is in refrigerator section" + "bestBefore: " + bestBefore;
    }
}
