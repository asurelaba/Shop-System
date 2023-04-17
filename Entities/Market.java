package Entities;

/*
 * Market class is a type of Asile that represents the open market area to display all the fresh produce.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Market extends Asile{
    private String displayZone;

    public Market(int asileNum, int capacity, int numOfShelves, String displayZone) {
        super(asileNum, capacity, numOfShelves);
        this.displayZone = displayZone;
    }

    public String getDisplayZone() {
        return displayZone;
    }

    public void setDisplayZone(String displayZone) {
        this.displayZone = displayZone;
    }
}
