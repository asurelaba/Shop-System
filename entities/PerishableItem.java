import java.time.LocalDate;
import java.util.Date;
/*
 * PerishableItem represents the items that have expiry date.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */


public class PerishableItem extends Item {
    protected LocalDate bestBefore;

    public PerishableItem(int itemNo, String name, String brand, float price, int quantityInAsile, int quantityInInventory, int maxQuantity, Asile asile, Supplier supplier, LocalDate bestBefore) {
        super(itemNo, name, brand, price, quantityInAsile, quantityInInventory, maxQuantity, asile, supplier);
        this.bestBefore = bestBefore;
    }

    public PerishableItem(int itemNo, String name, String brand, int quantityInAsile, int quantityInInventory, int maxQuantity, Asile asile, Supplier supplier, LocalDate bestBefore) {
        super(itemNo, name, brand, quantityInAsile, quantityInInventory, maxQuantity, asile, supplier);
        this.bestBefore = bestBefore;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    public String findMyItem() {
        return itemNo + " " + itemName + "is in Asile " + asile + "bestBefore: " + bestBefore;
    }
}
