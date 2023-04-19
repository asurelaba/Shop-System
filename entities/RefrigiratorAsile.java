package entities;
/*
 * Refrigirator class represents refrigirator asile.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class RefrigiratorAsile extends Asile {
    private float temp;

    public RefrigiratorAsile(int asileNum, int capacity, int numOfShelves, float temp) {
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
