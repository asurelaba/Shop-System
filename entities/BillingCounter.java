package entities;

import java.util.ArrayList;
import java.util.Calendar;

/*
 * BillingCounter class represents checkout counters.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * change log
 * 04/18/2023 added checkout method and change array receipts to ArrayList
 * */

public class BillingCounter {

    private int counterNum;
    private Employee employee;
    private ArrayList<Receipt> receipts;

    public BillingCounter(int counterNum, Employee employee) {
        this.counterNum = counterNum;
        this.employee = employee;
        this.receipts = new ArrayList<Receipt>();
    }

    public int getCounterNum() {
        return counterNum;
    }

    public void setCounterNum(int counterNum) {
        this.counterNum = counterNum;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void checkout(Shop shop, Customer customer, ArrayList<Item> items) {
        Receipt.updateTotalReceipts();
        float total = 0;
        for (Item item : items) {
            total += item.getPrice();
            item.setQuantity(item.quantity-1);
        }
        Receipt receipt = new Receipt(Receipt.getTotalReceipts(), total, employee.getName(), items, Calendar.getInstance().getTime(), this);
        customer.setReceipt(receipt);
        receipts.add(receipt);
        shop.updateNetGain(total);
        System.out.println("Thanks for shopping at " + shop.getShopName() + ". Amount due: " + total);
        System.out.println(receipt);
    }


}
