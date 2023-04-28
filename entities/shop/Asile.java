package shop;

/*
 * Asile class represents asiles and the items they hold.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

import java.util.ArrayList;
import java.util.Objects;

public class Asile {
    protected int asileNum;
    protected int capacity;
    protected int numOfShelves;
    protected ArrayList<Item> itemsInShelf;

    public Asile(int asileNum, int capacity, int numOfShelves) {
        this.asileNum = asileNum;
        this.capacity = capacity;
        this.numOfShelves = numOfShelves;
        this.itemsInShelf = new ArrayList<Item>();
    }

    public Asile(int asileNum, int capacity, int numOfShelves, ArrayList<Item> itemsInShelf) {
        this.asileNum = asileNum;
        this.capacity = capacity;
        this.numOfShelves = numOfShelves;
        this.itemsInShelf = itemsInShelf;
    }

    public int getAsileNum() {
        return asileNum;
    }

    public void setAsileNum(int asileNum) {
        this.asileNum = asileNum;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumOfShelves() {
        return numOfShelves;
    }

    public void setNumOfShelves(int numOfShelves) {
        this.numOfShelves = numOfShelves;
    }

    public ArrayList<Item> getItemsInShelf() {
        return itemsInShelf;
    }

    public void setItemsInShelf(ArrayList<Item> itemsInShelf) {
        this.itemsInShelf = itemsInShelf;
    }

    @Override
    public String toString() {
        return asileNum + " ";
    }

    @Override
    public int hashCode() {
        return Objects.hash(asileNum, capacity, numOfShelves);
    }

    @Override
    protected Asile clone() throws CloneNotSupportedException {
        return new Asile(asileNum, capacity, numOfShelves, itemsInShelf);
    }
}
