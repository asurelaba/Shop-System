package entities.shop;

/*
 * Market class is a type of Asile that represents the open market area to display all the fresh produce.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

import entities.enums.DisplayZone;

public class MarketAsile extends Asile {
    private DisplayZone displayZone;

    public MarketAsile(int asileNum, int capacity, int numOfShelves, DisplayZone displayZone) {
        super(asileNum, capacity, numOfShelves);
        this.displayZone = displayZone;
    }

    public DisplayZone getDisplayZone() {
        return displayZone;
    }

    public void setDisplayZone(DisplayZone displayZone) {
        this.displayZone = displayZone;
    }
}
