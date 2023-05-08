package entities.shop;

/*
 * NonPerishableItem represents the items that do not have expiry date.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class NonPerishableItem extends Item {
    private float weight;

    public NonPerishableItem(int itemNo, String name, String brand, float price, int quantityInAsile, int quantityInInventory, int maxQuantity, Asile asile, Supplier supplier, float weight) {
        super(itemNo, name, brand, price, quantityInAsile, quantityInInventory, maxQuantity, asile, supplier);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}