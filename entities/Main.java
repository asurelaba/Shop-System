package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
/*
 * Main class - starting point of the application. Has main method implementation.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */


public class Main {
    public static void main(String[] args) {
        Supplier supplierFruit = new Supplier(1, "SupplierFruit");
        Supplier supplierVeggie = new Supplier(2, "SupplierVeggie");
        Supplier supplierCleaning = new Supplier(3, "SupplierCleaning");
        Supplier supplierPerishable = new Supplier(4, "SupplierPerishable");

        Employee employee1 = new Employee(1, "person1", "xyz 1234", "1231231234",
                111, "Manager", 10000);
        Manager manager1 = new Manager(employee1, "store lock \n leave approval");
        Employee employee2 = new Employee(2, "person2", "bc 1234", "12312312567",
                222, "Billing", 1000, manager1);
        Employee employee3 = new Employee(3, "person3", "efg 1234", "12312318777",
                333, "AsileMaintaence", 10000, manager1);

        Asile asile1 = new Asile(1, 20, 4);
        Asile asile2 = new Asile(2, 20, 4);
        Asile asile3 = new Asile(3, 20, 4);

        MarketAsile marketFront = new MarketAsile(20, 100, 1, "Front");
        MarketAsile marketCenter = new MarketAsile(21, 30, 1, "Center");
        MarketAsile marketBack = new MarketAsile(22, 30, 1, "Back");

        RefrigiratorAsile refrigirator1 = new RefrigiratorAsile(40, 40, 3, 30.5f);
        RefrigiratorAsile refrigirator2 = new RefrigiratorAsile(40, 40, 3, 30.5f);

        FreezerAsile freezer1 = new FreezerAsile(50, 20, 5, -12.0f);

        FreshProduceItem apple = new FreshProduceItem(1, "apple", "Dole", 12f, 100, 100,
                marketFront, supplierFruit, "2023-01-01", 1.2f);
        FreshProduceItem orange = new FreshProduceItem(2, "orange", "Dole", 10f, 50, 50,
                marketFront, supplierFruit, "2023-01-04", 1.5f);

        PerishableItem oreo = new PerishableItem(3, "oreo", "Pepsico", 3, 12,
                asile1, supplierPerishable, "2023-01-04");

        NonPerishableItem lyzol = new NonPerishableItem(5, "lyzol", "p&g", 5.0f, 10,
                10, asile3, supplierCleaning, 1f);

        FrozenItem iceCream = new FrozenItem(10, "iceCream", "Brusters", 3.2f, 30, 30,
                freezer1, supplierPerishable, "2023-07-05", -10f);

        DiaryItem milk = new DiaryItem(50, "Whole milk", "Kirkland", 4.0f, 50,
                50, refrigirator1, supplierPerishable, "2023-05-01");

        BillingCounter counter1 = new BillingCounter(1, employee2);
        BillingCounter counter2 = new BillingCounter(2, employee1);

        ArrayList<Item> items = new ArrayList<Item>(Arrays.asList(milk, iceCream, apple));
        Receipt receipt1 = new Receipt(1001, 20.0f, employee2.getName(), items, new Date(), counter1);

        Customer customer1 = new Customer(7, "person7", "xyz 1234", "1231231234", receipt1);
        Customer customer2 = new Customer(8, "person8", "abc 1234", "12312312567");
        Customer customer3 = new Customer(9, "person9", "efg 1234", "12312318777");
        Customer customer4 = new Customer(10, "person10", "ght 1234", "12312312766");

        ArrayList<Item> inventoryArr = new ArrayList<Item>();
        System.out.println(iceCream.findMyItem());
        inventoryArr.add(iceCream);
        inventoryArr.add(orange);
        System.out.println(lyzol.findMyItem());
        inventoryArr.add(lyzol);
        System.out.println(milk.findMyItem());
        inventoryArr.add(oreo);
        Inventory inventory = new Inventory(inventoryArr);

        //creating the shop with inventory, employees and asiles
        Shop shop = new Shop("Costco");

        shop.setInventory(inventory);

        shop.addEmployee(employee1);
        shop.addEmployee(employee2);
        shop.addEmployee(employee3);

        shop.addAsile(asile1);
        shop.addAsile(asile2);
        shop.addAsile(asile3);

        shop.addSupplier(supplierCleaning);
        shop.addSupplier(supplierVeggie);
        shop.addSupplier(supplierFruit);
        shop.addSupplier(supplierPerishable);

        shop.addFreezer(freezer1);

        shop.addRefrigirator(refrigirator1);
        shop.addRefrigirator(refrigirator2);

        shop.addMarket(marketBack);
        shop.addMarket(marketCenter);
        shop.addMarket(marketFront);

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

        Employee employee4 = new Employee(3, "person3", "efg 1234", "12312318777",
                333, "AsileMaintaence", 10000, manager1);
        Employee employee5 = new Employee(3, "perso3", "efg 1234", "12312318777",
                333, "AsileMaintaence", 10000, manager1);

        System.out.println("Employees are same? : " + employee4.equals(employee5));
    }
}
