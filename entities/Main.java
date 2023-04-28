import interfaces.IMaintainColdSection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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

    public static void main(String[] args) {
        Supplier supplierFruit = new Supplier(1, "SupplierFruit");
        Supplier supplierVeggie = new Supplier(2, "SupplierVeggie");
        Supplier supplierCleaning = new Supplier(3, "SupplierCleaning");
        Supplier supplierPerishable = new Supplier(4, "SupplierPerishable");

        Employee employee1 = new Employee(1, "person1", "xyz 1234", "1231231234", 111, "Manager", 10000);
        Manager manager1 = new Manager(employee1, "store lock \n leave approval");
        Employee employee2 = new Employee(2, "person2", "bc 1234", "12312312567", 222, "Billing", 1000, manager1);
        Employee employee3 = new Employee(3, "person3", "efg 1234", "12312318777", 333, "AsileMaintaence", 10000, manager1);

        Asile asile1 = new Asile(1, 20, 4);
        Asile asile2 = new Asile(2, 20, 4);
        Asile asile3 = new Asile(3, 20, 4);

        MarketAsile marketFront = new MarketAsile(20, 100, 1, "Front");
        MarketAsile marketCenter = new MarketAsile(21, 30, 1, "Center");
        MarketAsile marketBack = new MarketAsile(22, 30, 1, "Back");

        RefrigiratorAsile refrigirator1 = new RefrigiratorAsile(40, 40, 3, 30.5f);
        RefrigiratorAsile refrigirator2 = new RefrigiratorAsile(41, 40, 3, 30.5f);

        FreezerAsile freezer1 = new FreezerAsile(50, 20, 5, -12.0f);

        FreshProduceItem apple = new FreshProduceItem(1, "apple", "Dole", 12f, 100, 0, 100, marketFront, supplierFruit, LocalDate.parse("2023-01-01"), 1.2f);
        FreshProduceItem orange = new FreshProduceItem(2, "orange", "Dole", 10f, 50, 0, 50, marketFront, supplierFruit, LocalDate.parse("2023-01-04"), 1.5f);

        PerishableItem oreo = new PerishableItem(3, "oreo", "Pepsico", 0, 3, 12, asile1, supplierPerishable, LocalDate.parse("2023-02-02"));

        NonPerishableItem lyzol = new NonPerishableItem(5, "lyzol", "p&g", 5.0f, 0, 10, 10, asile3, supplierCleaning, 1f);

        FrozenItem iceCream = new FrozenItem(10, "iceCream", "Brusters", 3.2f, 0, 30, 30, freezer1, supplierPerishable, LocalDate.parse("2023-07-01"), -10f);

        DiaryItem milk = new DiaryItem(50, "Whole milk", "Kirkland", 4.0f, 0, 50, 50, refrigirator1, supplierPerishable, LocalDate.parse("2023-01-10"));
        DiaryItem yogurt = new DiaryItem(51, "yogurt", "Kirkland", 4.0f, 0, 50, 50, refrigirator1, supplierPerishable, LocalDate.parse("2023-12-10"));
        DiaryItem cheese = new DiaryItem(52, "Cheese", "Kirkland", 4.0f, 0, 50, 50, refrigirator1, supplierPerishable, LocalDate.parse("2023-01-10"));

        BillingCounter counter1 = new BillingCounter(1, employee2);
        BillingCounter counter2 = new BillingCounter(2, employee1);

        ArrayList<Item> items = new ArrayList<Item>(Arrays.asList(milk, iceCream, apple));
        Receipt receipt1 = new Receipt(1001, 20.0f, employee2.getName(), items, new Date(), counter1);

        Customer customer1 = new Customer(7, "person7", "xyz 1234", "1231231234", receipt1);
        Customer customer2 = new Customer(8, "person8", "abc 1234", "12312312567");
        Customer customer3 = new Customer(9, "person9", "efg 1234", "12312318777");
        Customer customer4 = new Customer(10, "person10", "ght 1234", "12312312766");

        ArrayList<Item> inventoryArr = new ArrayList<Item>();
        //System.out.println(iceCream.findMyItem());
        inventoryArr.add(iceCream);
        inventoryArr.add(orange);
        //System.out.println(lyzol.findMyItem());
        inventoryArr.add(lyzol);
        //System.out.println(milk.findMyItem());
        inventoryArr.add(oreo);
        inventoryArr.add(milk);
        inventoryArr.add(yogurt);
        inventoryArr.add(cheese);
        Inventory inventory = new Inventory(inventoryArr);

        //creating the shop with inventory, employees and asiles
        Shop shop = new Shop("Costco");

        shop.setInventory(inventory);
        shop.printItemsInShop();

        shop.addEmployee(employee1);
        shop.addEmployee(employee2);
        shop.addEmployee(employee3);

        shop.addAsile(asile1);
        shop.addAsile(asile2);
        shop.addAsile(asile3);
        shop.addAsile(refrigirator1);
        shop.addAsile(refrigirator2);
        shop.addAsile(freezer1);
        shop.addAsile(marketBack);
        shop.addAsile(marketCenter);
        shop.addAsile(marketFront);

        inventory.addItemToAsile(shop);

        shop.addSupplier(supplierCleaning);
        shop.addSupplier(supplierVeggie);
        shop.addSupplier(supplierFruit);
        shop.addSupplier(supplierPerishable);

        shop.addBillingCounter(counter1);
        shop.addBillingCounter(counter2);

        shop.addCustomer(customer1);


        //customer adding items in the cart and checkout
        ArrayList<Item> itemsInCart = new ArrayList<Item>();
        itemsInCart.add(iceCream);
        itemsInCart.add(orange);
        customer1.setReceipt(counter1.checkout(shop, customer1, itemsInCart));
        System.out.println(customer1.getReceipt());
        //check for the stock and order from supplier if stock is low.
        if (shop.getInventory().needToRestock()) {
            shop.placeOrder();
        }

        ArrayList<Item> itemsInCart2 = new ArrayList<Item>();
        itemsInCart2.add(iceCream);
        itemsInCart2.add(oreo);
        customer2.setReceipt(counter2.checkout(shop, customer2, itemsInCart2));
        System.out.println(customer2.getReceipt());

        shop.getInventory().needToRestock();

        shop.printItemsInShop();

        customer2.printDetails();
        employee1.printDetails();
        employee3.printDetails();

        Employee employee4 = new Employee(4, "person4", "efg 1234", "12312318777", 333, "AsileMaintaence", 10000, manager1);
        Employee employee5 = new Employee(5, "perso5", "efg 1234", "12312318777", 333, "AsileMaintaence", 10000, manager1);

        System.out.println("Employees are same? : " + employee4.equals(employee5));

        System.out.println(refrigirator1);
        shop.removePerishableBeforeExpiry();
        System.out.println(refrigirator1);
        shop.printItemsInShop();

        shop.increaseSalaryForEmployees(2.0f);

        freezer1.setTemp(90f);
        shop.addFreezer(freezer1);
        LOGGER.debug(freezer1.getTemp() + "  " + IMaintainColdSection.REFRIGIRATOR_MIN_TEMP);
        shop.maintainTempForAllFreezer();

        shop.isMinWageMetForEmployeesUnder(manager1);
        employee2.setShiftStartTime(Calendar.getInstance().getTime());
        employee2.setShiftEndTime(Calendar.getInstance().getTime());
        employee3.setShiftStartTime(Calendar.getInstance().getTime());
        employee3.setShiftEndTime(Calendar.getInstance().getTime());
        employee4.setShiftStartTime(Calendar.getInstance().getTime());
        employee4.setShiftEndTime(Calendar.getInstance().getTime());
        employee5.setShiftStartTime(Calendar.getInstance().getTime());
        employee5.setShiftEndTime(Calendar.getInstance().getTime());
        for (Employee em : manager1.getEmployees()) {
            LOGGER.debug(em.getName() + " " + em.getShiftEndTime() + " " + em.getShiftStartTime());
        }
        manager1.checkEmployeeWorkingHours();

        if (!shop.hasFoodHandlingProcess()) {
            LOGGER.fatal("The shop will be closed due to food safety violations.");
        }


        ArrayList<Item> supplierFilledOrder = new ArrayList<>();
        supplierFilledOrder.add(oreo);
        shop.getInventory().incomingItemsFromSupplier(supplierFilledOrder);
    }
}
