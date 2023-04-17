package Entities;

import java.util.Date;

/*
 * Receipt class represents receipt generated during the billing at the billing counter.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Receipt {
    private int receiptNum;
    private float total;
    private Item[] items;
    private Date shoppedDate;
    private BillingCounter counter;

    public Receipt(int receiptNum, float total, Item[] items, Date shoppedDate, BillingCounter counter) {
        this.receiptNum = receiptNum;
        this.total = total;
        this.items = items;
        this.shoppedDate = shoppedDate;
        this.counter = counter;
    }

    public int getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(int receiptNum) {
        this.receiptNum = receiptNum;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Date getShoppedDate() {
        return shoppedDate;
    }

    public void setShoppedDate(Date shoppedDate) {
        this.shoppedDate = shoppedDate;
    }

    public BillingCounter getCounter() {
        return counter;
    }

    public void setCounter(BillingCounter counter) {
        this.counter = counter;
    }
}
