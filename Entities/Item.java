package Entities;
import java.util.Date;

/*
 * Item class represents all the goods that are in the store.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */


public class Item {
    protected int itemNo;
    protected String name;
    protected String brand;
    protected float price;
    protected int quantity;
    protected int maxQuantity;
    protected Asile asile;
    protected Supplier supplier;

    public Item(int itemNo, String name, String brand, float price,
                int quantity, int maxQuantity, Asile asile, Supplier supplier) {
        this.itemNo = itemNo;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.asile = asile;
        this.supplier = supplier;
    }

    public Item(int itemNo, String name, String brand, int quantity, int maxQuantity, Asile asile, Supplier supplier) {
        this.itemNo = itemNo;
        this.name = name;
        this.brand = brand;
        this.quantity = quantity;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
