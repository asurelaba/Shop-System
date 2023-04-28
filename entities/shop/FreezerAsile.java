package shop;

import customexceptions.FreezerAsileNotWorkingException;
import interfaces.IMaintainColdSection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger LOGGER = LogManager.getLogger(FreezerAsile.class);
    private float temp;
    private Date lastCleaned;
    private boolean hasBackup;

    public FreezerAsile(int asileNum, int capacity, int numOfShelves, float temp) {
        super(asileNum, capacity, numOfShelves);
        this.temp = temp;
        this.hasBackup = false;
    }

    public FreezerAsile(int asileNum, int capacity, int numOfShelves, ArrayList<Item> itemsInShelf, float temp, boolean hasBackup) {
        super(asileNum, capacity, numOfShelves, itemsInShelf);
        this.temp = temp;
        this.hasBackup = hasBackup;
        this.lastCleaned = Calendar.getInstance().getTime();
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
    public void maintainTemp() throws FreezerAsileNotWorkingException {
        LOGGER.debug("in maintainTemp  " + temp);
        if (temp > IMaintainColdSection.REFRIGIRATOR_MIN_TEMP) {
            System.out.println("Freezer " + asileNum + "is not working. Please replace");
            throw new FreezerAsileNotWorkingException("Freezer " + asileNum + "is not working. Please replace");
        }
        if (temp > IMaintainColdSection.FREEZER_MIN_TEMP) {
            temp = IMaintainColdSection.FREEZER_MIN_TEMP;
        }
    }

    @Override
    protected FreezerAsile clone() throws CloneNotSupportedException {
        return new FreezerAsile(asileNum, capacity, numOfShelves, itemsInShelf, temp, true);
    }
}
