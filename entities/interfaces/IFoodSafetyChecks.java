package interfaces;

/*
 * IFoodSafetyChecks interface is to ensure the food safey regulations.
 *
 * @version 1.0 21 Apr 2023
 * @author Ashwini Suresh
 * */

public interface IFoodSafetyChecks {
    boolean hasFoodHandlingProcess();

    void removePerishableBeforeExpiry();
}
