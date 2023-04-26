package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Diary class represents the diary products.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class FreezerAsile extends Asile implements IMaintainColdSection {
    private float temp;
    private Date lastCleaned;
    private boolean hasBackup;

    public FreezerAsile(int asileNum, int capacity, int numOfShelves, float temp) {
        super(asileNum, capacity, numOfShelves);
        this.temp = temp;
        this.hasBackup = false;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public Date getLastCleanedDate() {
        return lastCleaned;
    }

    public void setLastCleanedDate(Date lastCleaned) {
        this.lastCleaned = lastCleaned;
    }

    @Override
    public boolean isColdStorageCleaned() {
        return (Calendar.getInstance().getTime().compareTo(lastCleaned) < 7) ? true : false;
    }

    @Override
    public boolean hasBackupPower() {
        return hasBackup;
    }

    @Override
    public boolean hasFoodHandlingProcess() {
        return true;
    }

    @Override
    public void removePerishableBeforeExpiry() {
        PerishableItem perishableItem;
        int i = 0;
        while (i < itemsInShelf.size()) {
            perishableItem = (PerishableItem) itemsInShelf.get(i);
            if (LocalDate.now().isAfter(perishableItem.bestBefore)) {
                itemsInShelf.remove(perishableItem);
                continue;
            }
            i++;
        }
    }

    @Override
    public void maintainTemp() {
        if (temp > IMaintainColdSection.FREEZER_MIN_TEMP) {
            temp = IMaintainColdSection.FREEZER_MIN_TEMP;
        }
    }
}
