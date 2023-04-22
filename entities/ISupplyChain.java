package entities;

/*
 * ISupplyChain interface enforce regular checks to stocks and show the inventory.
 *
 * @version 1.0 20 Apr 2023
 * @author Ashwini Suresh
 *
 * */

public interface ISupplyChain {
    boolean needToRestock();

    void printInventory();
}
