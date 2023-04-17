package Entities;

/*
 * Asile class represents asiles and the items they hold.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Asile {
    private int asileNum;
    private int capacity;
    private int numOfShelves;
    private Item[] itemsInShelf;

    public Asile(int asileNum, int capacity, int numOfShelves) {
        this.asileNum = asileNum;
        this.capacity = capacity;
        this.numOfShelves = numOfShelves;
        this.itemsInShelf = new Item[capacity];
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

    public Item[] getItemsInShelf() {
        return itemsInShelf;
    }

    public void setItemsInShelf(Item[] itemsInShelf) {
        this.itemsInShelf = itemsInShelf;
    }
}
