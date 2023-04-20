package entities;

/*
 * NonPerishableItem represents the items that do not have expiry date.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class NonPerishableItem extends Item {
    private float weight;

    public NonPerishableItem(int itemNo, String name, String brand, float price, int quantity, int maxQuantity, Asile asile, Supplier supplier, float weight) {
        super(itemNo, name, brand, price, quantity, maxQuantity, asile, supplier);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
