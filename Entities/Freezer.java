package Entities;
/*
 * Diary class represents the diary products.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Freezer extends Asile {
    private float temp;

    public Freezer(int asileNum, int capacity, int numOfShelves, float temp) {
        super(asileNum, capacity, numOfShelves);
        this.temp = temp;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
}
