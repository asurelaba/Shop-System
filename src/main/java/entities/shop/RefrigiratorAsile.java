package entities.shop;

/*
 * Refrigirator class represents refrigirator asile.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

import entities.customexceptions.RefrigiratorAsileNotWorkingException;
import entities.interfaces.IMaintainColdSection;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class RefrigiratorAsile extends Asile implements IMaintainColdSection {
    private float temp;
    private boolean hasBackup;

    public RefrigiratorAsile(int asileNum, int capacity, int numOfShelves, float temp) {
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

    public void setHasBackup(boolean hasBackup) {
        this.hasBackup = hasBackup;
    }

    @Override
    public boolean isColdStorageCleaned() {
        return false;
    }

    @Override
    public boolean hasBackupPower() {
        return hasBackup;
    }

    @Override
    public void maintainTemp() throws RefrigiratorAsileNotWorkingException {
        if (temp > 90f) {
            throw new RefrigiratorAsileNotWorkingException("Refrigirator not working. Please replace");
        }
        if (temp < IMaintainColdSection.REFRIGIRATOR_MIN_TEMP && temp > IMaintainColdSection.FREEZER_MIN_TEMP) {
            temp = IMaintainColdSection.REFRIGIRATOR_MIN_TEMP;
        }
    }

    @Override
    public boolean hasFoodHandlingProcess() {
        return false;
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
    public String toString() {
        return "RefrigiratorAsile{" + "asileNum=" + asileNum + ", itemsInShelf=" + itemsInShelf + '}';
    }
}
