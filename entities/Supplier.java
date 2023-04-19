package entities;

import java.util.ArrayList;

/*
 * Refrigirator class represents refrigirator asile.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Supplier {
    private int supplierNum;
    private String supplierName;
    private ArrayList<Item> items;

    public Supplier(int supplierNum, String supplierName) {
        this.supplierNum = supplierNum;
        this.supplierName = supplierName;
        this.items = new ArrayList<Item>();
    }

    public int getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(int supplierNum) {
        this.supplierNum = supplierNum;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }


}
