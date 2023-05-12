package entities.shop;

import entities.enums.ItemType;

import java.time.LocalDate;

/*
 * FreshProduce class represents all the fruits and vegetables.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */


public final class FreshProduceItem extends PerishableItem {
    private float pricePerPound;

    public FreshProduceItem(int itemNo, String name, ItemType itemType, String brand, float price, int quantityInAsile, int quantityInInventory, int maxQuantity, Asile asile, Supplier supplier, LocalDate bestBefore, float pricePerPound) {
        super(itemNo, name, itemType, brand, price, quantityInAsile, quantityInInventory, maxQuantity, asile, supplier, bestBefore);
        this.pricePerPound = pricePerPound;
    }

    public float getPricePerPound() {
        return pricePerPound;
    }

    public void setPricePerPound(float pricePerPound) {
        this.pricePerPound = pricePerPound;
    }
}
