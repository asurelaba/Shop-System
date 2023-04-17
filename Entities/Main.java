package Entities;
/*
 * Main class - starting point of the application. Has main method implementation.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

import java.text.DateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Supplier supplierFruit = new Supplier(1, "SupplierFruit");
        Supplier supplierVeggie = new Supplier(2, "SupplierVeggie");
        Supplier supplierCleaning = new Supplier(3, "SupplierCleaning");
        Supplier supplierPerishable = new Supplier(4, "SupplierPerishable");

        Person person1 = new Person(1, "person1", "xyz 1234", "1231231234");
        Person person2 = new Person(2, "person2", "abc 1234", "12312312567");
        Person person3 = new Person(3, "person3", "efg 1234", "12312318777");
        Person person4 = new Person(4, "person4", "ght 1234", "12312312766");

        Employee employee1 = new Employee(1, "person1", "xyz 1234", "1231231234",
                111, "Manager", 10000);
        Employee employee2 = new Employee(2, "person2", "bc 1234", "12312312567",
                111, "Billing", 1000, employee1);
        Employee employee3 = new Employee(3, "person3", "efg 1234", "12312318777",
                111, "AsileMaintaence", 10000, employee1);

        Asile asile1 = new Asile(1, 20, 4);
        Asile asile2 = new Asile(2, 20, 4);
        Asile asile3 = new Asile(3, 20, 4);

        Market marketFront = new Market(20, 100, 1, "Front");
        Market marketCenter = new Market(21, 30, 1, "Center");
        Market marketBack = new Market(22, 30, 1, "Back");

        Refrigirator refrigirator1 = new Refrigirator(40, 40, 3, 30.5f);

        Freezer freezer1 = new Freezer(50, 20, 5, -12.0f);

        FreshProduce apple = new FreshProduce(1, "apple", "Dole", 12f, 100, 100,
                marketFront, supplierFruit, "2023-01-01", 1.2f);
        FreshProduce orange = new FreshProduce(2, "orange", "Dole", 10f, 50, 50,
                marketFront, supplierFruit, "2023-01-04", 1.5f);

        PerishableItem oreo = new PerishableItem(3, "oreo", "Pepsico", 5, 12,
                asile1, supplierPerishable, "2023-01-04");

        NonPerishableItem lyzol = new NonPerishableItem(5, "lyzol", "p&g", 5.0f, 10,
                10, asile3, supplierCleaning, 1f);

        Frozen iceCream = new Frozen(10, "iceCream", "Brusters", 3.2f, 30, 30,
                freezer1, supplierPerishable, "2023-07-05", -10f);

        Diary milk = new Diary(50, "Whole milk", "Kirkland", 4.0f, 50,
                50, refrigirator1, supplierPerishable, "2023-05-01");

        BillingCounter counter1 = new BillingCounter(1, employee2);
        BillingCounter counter2 = new BillingCounter(2, employee1);

        Item[] items = {milk, iceCream, apple};
        Receipt receipt1 = new Receipt(1001, 20.0f, items, new Date(), counter1);
        Customer customer1 = new Customer(person4, receipt1);

        Item[] inventoryArr = {milk, iceCream, apple, orange, lyzol, oreo};
        Inventory inventory = new Inventory(inventoryArr);

    }
}
