package entities;

import entities.datasetup.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import entities.people.*;
import entities.shop.*;

/*
 * Main class - starting point of the application. Has main method implementation.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static DataProvider dataProvider = new DataProvider();

    public static void main(String[] args) {
        //creating the shop with predefined data.
        Shop shop = dataProvider.setUpShop("Costco");

        //Customer shops
        ArrayList<Item> itemsInCart = new ArrayList<Item>();
        Item lookForItem = shop.getInventory().getItems().get(1);
        Customer customer1 = dataProvider.getCustomer("customer1");
        LOGGER.info("Customer " + customer1.getName() + " is finding item.." + lookForItem.getItemName());
        LOGGER.info(lookForItem.findMyItem());
        itemsInCart.add(lookForItem);
        itemsInCart.add(shop.getInventory().getItems().get(10));
        LOGGER.debug(itemsInCart);
        customer1.setReceipt(shop.getBillingCounters().first().checkout(shop, customer1, itemsInCart));
        LOGGER.info(customer1.getReceipt());

        ArrayList<Item> itemsInCart2 = new ArrayList<Item>();
        Customer customer2 = dataProvider.getCustomer("customer2");
        itemsInCart2.add(shop.getInventory().getItems().get(50));
        itemsInCart2.add(shop.getInventory().getItems().get(5));
        customer2.setReceipt(shop.getBillingCounters().last().checkout(shop, customer2, itemsInCart2));
        LOGGER.info(customer2.getReceipt());

        //shop maintainance for perishables
        shop.removePerishableBeforeExpiry();
        shop.printItemsInShop();

        //Check for minimum wage and working hours
        Manager manager1 = dataProvider.getManager("manager1");
        shop.isMinWageMetForEmployeesUnder(manager1);
        for (Employee em : manager1.getEmployees()) {
            LOGGER.debug(em.getName() + " " + em.getShiftEndTime() + " " + em.getShiftStartTime());
        }
        manager1.checkEmployeeWorkingHours();
        shop.increaseSalaryForEmployees(2.0f);

        //check for food safety
        if (!shop.hasFoodHandlingProcess()) {
            LOGGER.fatal("The shop will be closed due to food safety violations.");
        }

        //check for the stock and order from supplier if stock is low.
        if (shop.getInventory().needToRestock()) {
            shop.placeOrder();
        }

        //Supplier is filling the order
        ArrayList<Item> supplierFilledOrder = new ArrayList<>();
        supplierFilledOrder.add(shop.getInventory().getItems().get(5));
        shop.getInventory().incomingItemsFromSupplier(supplierFilledOrder);

        //regular maintenance for cold storage
        shop.maintainTempForAllFreezer();

        shop.displayCustomers();
        shop.getCustomers().remove(dataProvider.getCustomer("customer2"));
        LOGGER.info("Remaining customers after remove");
        shop.displayCustomers();
        shop.getCustomers().insert(2, dataProvider.getCustomer("customer2"));
        LOGGER.info(" customers after insert at position 2");
        shop.displayCustomers();
        LOGGER.info(" customers at position 1: " + shop.getCustomers().get(1));
    }
}
