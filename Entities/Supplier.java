package Entities;
/*
 * Refrigirator class represents refrigirator asile.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Supplier {
    int supplierNum;
    String supplierName;
    Item[] items;

    public Supplier(int supplierNum, String supplierName) {
        this.supplierNum = supplierNum;
        this.supplierName = supplierName;
    }

    public int getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(int supplierNum) {
        this.supplierNum = supplierNum;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
