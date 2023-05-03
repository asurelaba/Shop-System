package shop;

import customexceptions.ItemNotFilledBySupplierException;
import customexceptions.NoMoreItemInAsileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

/*
 * BillingCounter class represents checkout counters.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * change log
 * 04/18/2023 added checkout method and change array receipts to ArrayList
 * */

public class BillingCounter implements Comparable<BillingCounter> {
    private static final Logger LOGGER = LogManager.getLogger(BillingCounter.class);
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

    public Receipt checkout(Shop shop, Customer customer, ArrayList<Item> items) {
        float total = 0;
        for (Item item : items) {
            try {
                if (item.quantityInAsile < 2) {
                    throw new NoMoreItemInAsileException("Restock " + item + "in asile");
                }
            } catch (NoMoreItemInAsileException ex) {
                try {
                    shop.getInventory().restockItemInAsile(item);
                } catch (ItemNotFilledBySupplierException itemNotFilledBySupplier) {
                    shop.placeOrder();
                }
            }
            if (item.getClass() == FreshProduceItem.class) {
                FreshProduceItem freshProduceItem = (FreshProduceItem) item;
                total += calculatePrice(getWeightOfItemInCart(item), freshProduceItem.getPricePerPound());
                item.setQuantityInAsile(item.getQuantityInAsile() - 1);
                continue;
            }
            total += item.getPrice();
            item.setQuantityInAsile(item.getQuantityInAsile() - 1);
        }
        Receipt receipt = new Receipt(shop.getTotalReceiptsCount() + 1, total, employee.getName(), items, Calendar.getInstance().getTime(), this);
        receipts.add(receipt);
        shop.updateNetGain(total);
        LOGGER.info("Thanks for shopping at " + shop.getShopName() + ". Amount due: " + total);
        return receipt;
    }

    public int getReceiptsCount() {
        return receipts.size();
    }

    public static float calculatePrice(float weight, float pricePerPound) {
        return weight * pricePerPound;
    }

    public static float getWeightOfItemInCart(Item item) {
        Random random = new Random();
        if (item.getClass() == FreshProduceItem.class) {
            return random.nextFloat(10);
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingCounter that = (BillingCounter) o;
        return counterNum == that.counterNum && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counterNum, employee);
    }

    @Override
    public int compareTo(BillingCounter that) {
        return ((Integer)counterNum).compareTo(that.counterNum);
    }

    @Override
    public String toString() {
        return "BillingCounter{" +
                "counterNum=" + counterNum +
                ", employee=" + employee +
                '}';
    }
}
