import customlinkedlist.LinkedList;
import interfaces.IMaintainColdSection;
import interfaces.ISalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

import people.*;
import shop.*;

/*
 * Main class - starting point of the application. Has main method implementation.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static Shop shop = new Shop("Costco");

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private Customer customer4;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    private Manager manager1;

    public static void main(String[] args) {
        //creating the shop with inventory, employees and asiles
        Main costco = new Main();
        costco.setUpShop();

        //Customer shops
        ArrayList<Item> itemsInCart = new ArrayList<Item>();
        Item lookForItem = shop.getInventory().getItems().get(1);
        LOGGER.info("Customer " + costco.customer1.getName() + " is finding item.." + lookForItem.getItemName());
        LOGGER.info(lookForItem.findMyItem());
        itemsInCart.add(lookForItem);
        itemsInCart.add(shop.getInventory().getItems().get(10));
        LOGGER.debug(itemsInCart);
        costco.customer1.setReceipt(shop.getBillingCounters().first().checkout(shop, costco.customer1, itemsInCart));
        LOGGER.info(costco.customer1.getReceipt());

        ArrayList<Item> itemsInCart2 = new ArrayList<Item>();
        itemsInCart2.add(shop.getInventory().getItems().get(50));
        itemsInCart2.add(shop.getInventory().getItems().get(5));
        costco.customer2.setReceipt(shop.getBillingCounters().last().checkout(shop, costco.customer2, itemsInCart2));
        LOGGER.info(costco.customer2.getReceipt());

        //shop maintainance for perishables
        shop.removePerishableBeforeExpiry();
        shop.printItemsInShop();

        //Check for minimum wage and working hours
        shop.isMinWageMetForEmployeesUnder(costco.manager1);
        for (Employee em : costco.manager1.getEmployees()) {
            LOGGER.debug(em.getName() + " " + em.getShiftEndTime() + " " + em.getShiftStartTime());
        }
        costco.manager1.checkEmployeeWorkingHours();
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

        costco.displayCustomers();
    }

    public void setUpShop() {
        employee1 = new Employee(1, "person1", "xyz 1234", "1231231234", 111, "Manager", 10000);
        manager1 = new Manager(employee1, "store lock \n leave approval");
        employee2 = new Employee(2, "person2", "bc 1234", "12312312567", 222, "Billing", 1000, manager1);
        employee3 = new Employee(3, "person3", "efg 1234", "12312318777", 333, "AsileMaintaence", 10000, manager1);
        employee4 = new Employee(4, "person4", "efg 1234", "12312318777", 333, "AsileMaintaence", 10000, manager1);
        employee5 = new Employee(5, "perso5", "efg 1234", "12312318777", 333, "AsileMaintaence", 10000, manager1);
        employee2.setShiftStartTime(Calendar.getInstance().getTime());
        employee2.setShiftEndTime(Calendar.getInstance().getTime());
        employee3.setShiftStartTime(Calendar.getInstance().getTime());
        employee3.setShiftEndTime(Calendar.getInstance().getTime());
        employee4.setShiftStartTime(Calendar.getInstance().getTime());
        employee4.setShiftEndTime(Calendar.getInstance().getTime());
        employee5.setShiftStartTime(Calendar.getInstance().getTime());
        employee5.setShiftEndTime(Calendar.getInstance().getTime());
        shop.addEmployee(employee1);
        shop.addEmployee(employee2);
        shop.addEmployee(employee3);
        shop.addEmployee(employee4);
        shop.addEmployee(employee5);

        Supplier supplierFruit = new Supplier(1, "SupplierFruit");
        Supplier supplierVeggie = new Supplier(2, "SupplierVeggie");
        Supplier supplierCleaning = new Supplier(3, "SupplierCleaning");
        Supplier supplierPerishable = new Supplier(4, "SupplierPerishable");
        shop.addSupplier(supplierCleaning);
        shop.addSupplier(supplierVeggie);
        shop.addSupplier(supplierFruit);
        shop.addSupplier(supplierPerishable);

        Asile asile1 = new Asile(1, 20, 4);
        Asile asile2 = new Asile(2, 20, 4);
        Asile asile3 = new Asile(3, 20, 4);
        MarketAsile marketFront = new MarketAsile(20, 100, 1, "Front");
        MarketAsile marketCenter = new MarketAsile(21, 30, 1, "Center");
        MarketAsile marketBack = new MarketAsile(22, 30, 1, "Back");
        RefrigiratorAsile refrigirator1 = new RefrigiratorAsile(40, 40, 3, 30.5f);
        RefrigiratorAsile refrigirator2 = new RefrigiratorAsile(41, 40, 3, 30.5f);
        FreezerAsile freezer1 = new FreezerAsile(50, 20, 5, -12.0f);
        shop.addAsile(asile1);
        shop.addAsile(asile2);
        shop.addAsile(asile3);
        shop.addAsile(refrigirator1);
        shop.addAsile(refrigirator2);
        shop.addAsile(freezer1);
        shop.addAsile(marketBack);
        shop.addAsile(marketCenter);
        shop.addAsile(marketFront);
        freezer1.setTemp(90f);
        LOGGER.debug(freezer1.getTemp() + "  " + IMaintainColdSection.REFRIGIRATOR_MIN_TEMP);

        BillingCounter counter1 = new BillingCounter(1, shop.getEmployees().get(2));
        BillingCounter counter2 = new BillingCounter(2, shop.getEmployees().get(1));
        BillingCounter counter3 = new BillingCounter(3, shop.getEmployees().get(3));
        shop.addBillingCounter(counter1);
        shop.addBillingCounter(counter3);
        shop.addBillingCounter(counter2);
        shop.addBillingCounter(counter2);
        LOGGER.info("Counters are added to the shop " + shop.getBillingCounters());

        FreshProduceItem apple = new FreshProduceItem(1, "apple", "Dole", 12f, 100, 0, 100, marketFront, supplierFruit, LocalDate.parse("2023-01-01"), 1.2f);
        FreshProduceItem orange = new FreshProduceItem(2, "orange", "Dole", 10f, 50, 0, 50, marketFront, supplierFruit, LocalDate.parse("2023-01-04"), 1.5f);
        PerishableItem oreo = new PerishableItem(3, "oreo", "Pepsico", 0, 3, 12, asile1, supplierPerishable, LocalDate.parse("2023-02-02"));
        NonPerishableItem lyzol = new NonPerishableItem(5, "lyzol", "p&g", 5.0f, 0, 10, 10, asile3, supplierCleaning, 1f);
        FrozenItem iceCream = new FrozenItem(10, "iceCream", "Brusters", 3.2f, 0, 30, 30, freezer1, supplierPerishable, LocalDate.parse("2023-07-01"), -10f);
        DiaryItem milk = new DiaryItem(50, "Whole milk", "Kirkland", 4.0f, 0, 50, 50, refrigirator1, supplierPerishable, LocalDate.parse("2023-01-10"));
        DiaryItem yogurt = new DiaryItem(51, "yogurt", "Kirkland", 4.0f, 0, 50, 50, refrigirator1, supplierPerishable, LocalDate.parse("2023-12-10"));
        DiaryItem cheese = new DiaryItem(52, "Cheese", "Kirkland", 4.0f, 0, 50, 50, refrigirator1, supplierPerishable, LocalDate.parse("2023-01-10"));
        TreeMap<Integer, Item> inventoryArr = new TreeMap<>();
        inventoryArr.put(apple.getItemNo(), apple);
        inventoryArr.put(iceCream.getItemNo(), iceCream);
        inventoryArr.put(orange.getItemNo(), orange);
        inventoryArr.put(lyzol.getItemNo(), lyzol);
        inventoryArr.put(oreo.getItemNo(), oreo);
        inventoryArr.put(oreo.getItemNo(), oreo);
        inventoryArr.put(milk.getItemNo(), milk);
        inventoryArr.put(yogurt.getItemNo(), yogurt);
        inventoryArr.put(cheese.getItemNo(), cheese);
        Inventory inventory = new Inventory(inventoryArr);
        shop.setInventory(inventory);
        LOGGER.info("Inventory is added to Shop. List of items in inventory");
        inventory.printInventory();
        inventory.addItemToAsile(shop);
        LOGGER.info("Items are added to Asile");
        shop.printItemsInShop();

        customer1 = new Customer(7, "person7", "xyz 1234", "1231231234");
        customer2 = new Customer(8, "person8", "abc 1234", "12312312567");
        customer3 = new Customer(9, "person9", "efg 1234", "12312318777");
        customer4 = new Customer(10, "person10", "ght 1234", "12312312766");
        //adding customers to the custom linked list
        shop.addCustomer(customer1);
        shop.addCustomer(customer2);
        shop.addCustomer(customer3);
        shop.addCustomer(customer4);
    }

    public void displayCustomers() {
        LOGGER.info(" :::::::::::::::::::::::Customers of the shop ::::::::::::::::: ");
        LOGGER.info("There are " + shop.getCustomers().getSize() + " regular customers for " + shop.getShopName());
        LOGGER.info("Customer Details::");
        for (Customer customer : shop.getCustomers()) {
            LOGGER.info(customer);
        }
        shop.getCustomers().remove(customer2);
        LOGGER.info("Remaining customers after remove");
        for (Customer customer : shop.getCustomers()) {
            LOGGER.info(customer);
        }
        shop.getCustomers().insert(2, customer2);
        LOGGER.info(" customers after insert at position 2");
        for (Customer customer : shop.getCustomers()) {
            LOGGER.info(customer);
        }
        LOGGER.info(" customers at position 1: " + shop.getCustomers().get(1));
    }
}
