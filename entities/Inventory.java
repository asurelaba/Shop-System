import customexceptions.InvalidBestBeforeException;
import customexceptions.ItemNotFilledBySupplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * Inventory class holds the list of all the items in the store.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * change log
 * 04/18/2023 added needToRestock method
 * */
public class Inventory implements ISupplyChain {
    private static final Logger logger = LogManager.getLogger(Inventory.class);
    private ArrayList<Item> items;

    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }

    public Inventory() {
        items = new ArrayList<>();
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

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItemToAsile(Shop shop) {
        for (Item item : items) {
            for (Asile asile : shop.getAsiles()) {
                System.out.println(" item " + item.getItemName() + item.getAsile() + " asile " + asile.getAsileNum());
                if (item.getAsile().getAsileNum() == asile.getAsileNum()) {
                    asile.getItemsInShelf().add(item);
                    System.out.println("added item " + item.getItemName() + " to " + asile.getAsileNum());
                }
            }
        }
    }

    @Override
    public boolean needToRestock() {
        boolean shouldRestock = false;
        for (Item item : items) {
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
        System.out.println("------Inventory-----------");
        System.out.println("Item Name          Quantity");
        for (Item item : items) {
            System.out.println(item.getItemName() + " " + item.getQuantityInInventory());
        }
    }

    public void restockItemInAsile(Item item) throws ItemNotFilledBySupplier {
        if (item.quantityInInventory != 0 && item.quantityInInventory > 10) {
            item.setQuantityInAsile(item.quantityInInventory - 10);
        } else if (item.quantityInInventory < 10) {
            logger.warn("Item " + +item.itemNo + " " + item.getItemName() + "stock is low in Inventory. Adding Item to the supplier");
        } else {
            item.getSupplier().addItem(item);
            throw new ItemNotFilledBySupplier(item.itemNo + "  " + item.itemName + "is not filled by supplier. Please reorder");
        }
    }

    public void incomingItemsFromSupplier(ArrayList<Item> itemsFromSupplier) {
        logger.info("Order filled by Supplier. Quality check in progress...... . ");
        for (Item item : itemsFromSupplier) {
            if (item.getClass() == PerishableItem.class) {
                PerishableItem perishableItem = (PerishableItem) item;
                if (perishableItem.bestBefore.isBefore(LocalDate.now())) {
                    logger.warn(perishableItem.itemNo + " " + perishableItem.itemName + " is already expired. Rejecting item. Adding item to supplier order");
                    perishableItem.supplier.addItem(perishableItem);
                    continue;
                }
                if (perishableItem.bestBefore.compareTo(LocalDate.now()) < 5) {
                    throw new InvalidBestBeforeException("Invalid date close to expiry");
                }
            }
        }
        logger.info("Quality check Complete. Items are loaded to inventory. ");
    }
}
