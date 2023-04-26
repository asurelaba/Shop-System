package entities;

import java.util.ArrayList;

/*
 * Inventory class holds the list of all the items in the store.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * change log
 * 04/18/2023 added needToRestock method
 * */
public class Inventory implements ISupplyChain {
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
            if (item.getQuantity() < 5) {
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
            System.out.println(item.getItemName() + " " + item.getQuantity());
        }
    }
}
