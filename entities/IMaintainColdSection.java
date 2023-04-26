package entities;

/*
 * IMaintainColdSection interface is extension of food safety.
 *
 * @version 1.0 21 Apr 2023
 * @author Ashwini Suresh
 * */

public interface IMaintainColdSection extends IFoodSafetyChecks {
    float FREEZER_MIN_TEMP = 32;
    float REFRIGIRATOR_MIN_TEMP = 40;

    boolean isColdStorageCleaned();

    boolean hasBackupPower();

    void maintainTemp();
}
