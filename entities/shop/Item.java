package shop;

/*
 * Item class represents all the goods that are in the store.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */


import java.util.Objects;

public abstract class Item {
    protected int itemNo;
    protected String itemName;
    protected String brand;
    protected float price;
    protected int quantityInAsile;
    protected int quantityInInventory;
    protected int maxQuantity;
    protected Asile asile;
    protected Supplier supplier;

    public Item(int itemNo, String name, String brand, float price,
                int quantityInAsile, int quantityInInventory, int maxQuantity, Asile asile, Supplier supplier) {
        this.itemNo = itemNo;
        this.itemName = name;
        this.brand = brand;
        this.price = price;
        this.quantityInAsile = quantityInAsile;
        this.quantityInInventory = quantityInInventory;
        this.maxQuantity = maxQuantity;
        this.asile = asile;
        this.supplier = supplier;
    }

    public Item(int itemNo, String name, String brand, int quantityInAsile, int quantityInInventory, int maxQuantity, Asile asile, Supplier supplier) {
        this.itemNo = itemNo;
        this.itemName = name;
        this.brand = brand;
        this.quantityInAsile = quantityInAsile;
        this.quantityInInventory = quantityInInventory;
        this.maxQuantity = maxQuantity;
        this.asile = asile;
        this.supplier = supplier;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Asile getAsile() {
        return asile;
    }

    public void setAsile(Asile asile) {
        this.asile = asile;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getQuantityInAsile() {
        return quantityInAsile;
    }

    public void setQuantityInAsile(int quantityInAsile) {
        this.quantityInAsile = quantityInAsile;
    }

    public int getQuantityInInventory() {
        return quantityInInventory;
    }

    public void setQuantityInInventory(int quantityInInventory) {
        this.quantityInInventory = quantityInInventory;
    }

    public String findMyItem() {
        return itemNo + " " + itemName + "is in Asile " + asile;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemNo == item.itemNo && Objects.equals(itemName, item.itemName) && Objects.equals(brand, item.brand);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(itemNo, itemName, brand);
    }
}
