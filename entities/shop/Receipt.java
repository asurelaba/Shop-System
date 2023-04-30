package shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/*
 * Receipt class represents receipt generated during the billing at the billing counter.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Receipt {
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

    @Override
    public String toString() {
        StringBuffer printReceipt = new StringBuffer("\n-----------------------------------\n");
        printReceipt.append(receiptNum + "\n" + shoppedDate + "\n" + "served by: " + employeeName + "\n");
        for (Item item : items) {
            printReceipt.append(item.getItemName() + "      " + item.getPrice() + "\n");
        }
        printReceipt.append("Total:   " + total);
        printReceipt.append("\n-----------------------------------\n");
        return printReceipt.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return receiptNum == receipt.receiptNum && Float.compare(receipt.total, total) == 0 && Objects.equals(employeeName, receipt.employeeName) && Objects.equals(counter, receipt.counter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptNum, total, employeeName, counter);
    }
}
