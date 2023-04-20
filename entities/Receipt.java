package entities;

import java.util.ArrayList;
import java.util.Date;

/*
 * Receipt class represents receipt generated during the billing at the billing counter.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Receipt {

    private static int totalReceipts;
    private int receiptNum;
    private float total;
    private String employeeName;
    private ArrayList<Item> items;
    private Date shoppedDate;
    private BillingCounter counter;

    public Receipt(int receiptNum, float total, String employeeName, ArrayList<Item> items, Date shoppedDate, BillingCounter counter) {
        this.receiptNum = receiptNum;
        this.total = total;
        this.employeeName = employeeName;
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
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

    public static int getTotalReceipts() {
        return totalReceipts;
    }

    public static void updateTotalReceipts() {
        Receipt.totalReceipts += 1;
    }

    @Override
    public String toString() {
        StringBuffer printReceipt = new StringBuffer(receiptNum + "\n" + shoppedDate + "\n" + "served by: " + employeeName + "\n");
        for (Item item : items) {
            printReceipt.append(item.getName() + "      " + item.getPrice() + "\n");
        }
        printReceipt.append("Total:   " + total);
        return printReceipt.toString();
    }
}