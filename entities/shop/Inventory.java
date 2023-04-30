package shop;

import customexceptions.InvalidBestBeforeException;
import customexceptions.ItemNotFilledBySupplierException;
import interfaces.ISupplyChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;

/*
 * Inventory class holds the list of all the items in the store.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * change log
 * 04/18/2023 added needToRestock method
 * */
public class Inventory implements ISupplyChain {
    private static final Logger LOGGER = LogManager.getLogger(Inventory.class);
    //private ArrayList<Item> items;
    private TreeMap<Integer, Item> items;

    public Inventory(TreeMap<Integer, Item> items) {
        this.items = items;
    }

    public Inventory() {
        items = new TreeMap<>();
    }

    public TreeMap<Integer, Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.put(item.itemNo, item);
    }

    public void removeItem(Item item) {
        items.remove(item.itemNo);
    }

    public void addItemToAsile(Shop shop) {
        for (Item item : items.values()) {
            item.getAsile().itemsInShelf.add(item);
        }
    }

    @Override
    public boolean needToRestock() {
        boolean shouldRestock = false;
        for (Item item : items.values()) {
            if (item.getQuantityInAsile() < 5) {
                //add items to supplier order list
                item.getSupplier().addItem(item);
                shouldRestock = true;
            }
        }
        return shouldRestock;
    }

    @Override
    public void printInventory() {
        LOGGER.info("------Inventory-----------");
        LOGGER.info("Item Name          Quantity");
        for (Item item : items.values()) {
            LOGGER.info(item.getItemName() + " " + item.getQuantityInInventory());
        }
    }

    public void restockItemInAsile(Item item) throws ItemNotFilledBySupplierException {
        if (item.quantityInInventory != 0 && item.quantityInInventory > 10) {
            item.setQuantityInAsile(item.quantityInInventory - 10);
        } else if (item.quantityInInventory < 10) {
            LOGGER.warn("Item " + +item.itemNo + " " + item.getItemName() + "stock is low in Inventory. Adding Item to the supplier");
        } else {
            item.getSupplier().addItem(item);
            throw new ItemNotFilledBySupplierException(item.itemNo + "  " + item.itemName + "is not filled by supplier. Please reorder");
        }
    }

    public void incomingItemsFromSupplier(ArrayList<Item> itemsFromSupplier) {
        LOGGER.info("Order filled by Supplier. Quality check in progress...... . ");
        for (Item item : itemsFromSupplier) {
            if (item.getClass() == PerishableItem.class) {
                PerishableItem perishableItem = (PerishableItem) item;
                if (perishableItem.bestBefore.isBefore(LocalDate.now())) {
                    LOGGER.warn(perishableItem.itemNo + " " + perishableItem.itemName + " is already expired. Rejecting item. Adding item to supplier order");
                    perishableItem.supplier.addItem(perishableItem);
                    continue;
                }
                if (perishableItem.bestBefore.compareTo(LocalDate.now()) < 5) {
                    throw new InvalidBestBeforeException("Invalid date close to expiry");
                }
            }
        }
        LOGGER.info("Quality check Complete. Items are loaded to inventory. ");
    }
}
