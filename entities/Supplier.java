import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * Refrigirator class represents refrigirator asile.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Supplier {
    private static final Logger logger = LogManager.getLogger(Inventory.class);
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

    public void printOrderToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(supplierName + " order"));) {
            writer.write(items.toString());
        } catch (IOException ioException) {
            logger.error("Writing order to file failed...");
        }
    }

    @Override
    public String toString() {
        return ("Supplier Name : " + supplierName + "\nSupplier Id : " + supplierNum + "\n No. of items ordered: " + items.size());
    }
}
