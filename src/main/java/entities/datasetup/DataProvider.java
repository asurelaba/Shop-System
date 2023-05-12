package entities.datasetup;

import entities.enums.CounterStatus;
import entities.enums.DisplayZone;
import entities.enums.ItemType;
import entities.enums.Role;
import entities.interfaces.IMaintainColdSection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entities.people.Customer;
import entities.people.Employee;
import entities.people.Manager;
import entities.shop.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;

public class DataProvider {
    private static final Logger LOGGER = LogManager.getLogger(DataProvider.class);
    Shop shop;
    HashMap<String, Supplier> supplierHashMap = new HashMap<>();
    HashMap<String, Employee> employeeHashMap = new HashMap<>();
    HashMap<String, Manager> managerHashMap = new HashMap<>();
    HashMap<String, Customer> customerHashMap = new HashMap<>();
    HashMap<String, Asile> asileHashMap = new HashMap<>();

    public Employee getEmployee(String employeeName) {
        return employeeHashMap.get(employeeName);
    }

    public Customer getCustomer(String customerName) {
        return customerHashMap.get(customerName);
    }

    public Supplier getSupplier(String supplierName) {
        return supplierHashMap.get(supplierName);
    }

    public Asile getAsile(String asilename) {
        return asileHashMap.get(asilename);
    }

    public Manager getManager(String managerName) {
        return managerHashMap.get(managerName);
    }

    public Shop setUpShop(String shopName) {
        shop = new Shop(shopName);
        addPreDefinedEmployees();
        addPredefinedBillingCounters();
        addPreDefinedSuppliers();
        addPredefinedAsiles();
        addPredefinedItemsToInventory();
        addPreDefinedCustomers();
        return shop;
    }

    private void addPredefinedAsiles() {
        Asile asile1 = new Asile(1, 20, 4);
        Asile asile2 = new Asile(2, 20, 4);
        Asile asile3 = new Asile(3, 20, 4);
        MarketAsile marketFront = new MarketAsile(20, 100, 1, DisplayZone.FRONT);
        MarketAsile marketCenter = new MarketAsile(21, 30, 1, DisplayZone.CENTER);
        MarketAsile marketBack = new MarketAsile(22, 30, 1, DisplayZone.BACK);
        RefrigiratorAsile refrigirator1 = new RefrigiratorAsile(40, 40, 3, 30.5f);
        RefrigiratorAsile refrigirator2 = new RefrigiratorAsile(41, 40, 3, 30.5f);
        FreezerAsile freezer1 = new FreezerAsile(50, 20, 5, -12.0f);

        asileHashMap.put("asile1", asile1);
        asileHashMap.put("asile2", asile2);
        asileHashMap.put("asile3", asile3);
        asileHashMap.put("marketFront", marketFront);
        asileHashMap.put("marketCenter", marketCenter);
        asileHashMap.put("marketBack", marketBack);
        asileHashMap.put("refrigirator1", refrigirator1);
        asileHashMap.put("refrigirator2", refrigirator2);
        asileHashMap.put("freezer1", freezer1);

        shop.addAsile(asile1);
        shop.addAsile(asile2);
        shop.addAsile(asile3);
        shop.addAsile(refrigirator1);
        shop.addAsile(refrigirator2);
        shop.addAsile(freezer1);
        shop.addAsile(marketBack);
        shop.addAsile(marketCenter);
        shop.addAsile(marketFront);
        LOGGER.info("Asiles are added to the shop. Number of asiles: " + shop.getAsiles().size());

        freezer1.setTemp(90f);
        LOGGER.debug(freezer1.getTemp() + "  " + IMaintainColdSection.REFRIGIRATOR_MIN_TEMP);

    }

    private void addPredefinedBillingCounters() {
        BillingCounter counter1 = new BillingCounter(1, shop.getEmployees().get(2));
        BillingCounter counter2 = new BillingCounter(2, shop.getEmployees().get(1));
        BillingCounter counter3 = new BillingCounter(3, shop.getEmployees().get(3));
        shop.addBillingCounter(counter1);
        shop.addBillingCounter(counter3);
        shop.addBillingCounter(counter2);
        shop.addBillingCounter(counter2);
        counter2.setCounterStatus(CounterStatus.OPEN);
        LOGGER.info("Counters are added to the shop " + shop.getBillingCounters());
    }

    private void addPredefinedItemsToInventory() {
        FreshProduceItem apple = new FreshProduceItem(1, "apple", ItemType.FRUIT, "Dole", 12f, 100, 0, 100, asileHashMap.get("marketFront"), supplierHashMap.get("supplierFruit"), LocalDate.parse("2023-01-01"), 1.2f);
        FreshProduceItem orange = new FreshProduceItem(2, "orange", ItemType.FRUIT, "Dole", 10f, 50, 0, 50, asileHashMap.get("marketFront"), supplierHashMap.get("supplierFruit"), LocalDate.parse("2023-01-04"), 1.5f);
        PerishableItem oreo = new PerishableItem(3, "oreo", ItemType.COOKIE, "Pepsico", 0, 3, 12, asileHashMap.get("asile1"), supplierHashMap.get("supplierPerishable"), LocalDate.parse("2023-02-02"));
        NonPerishableItem lyzol = new NonPerishableItem(5, "lyzol", ItemType.CLEANING_PRODUCT, "p&g", 5.0f, 0, 10, 10, asileHashMap.get("asile3"), supplierHashMap.get("supplierCleaning"), 1f);
        FrozenItem iceCream = new FrozenItem(10, "iceCream", ItemType.FROZEN, "Brusters", 3.2f, 0, 30, 30, asileHashMap.get("freezer1"), supplierHashMap.get("supplierPerishable"), LocalDate.parse("2023-07-01"), -10f);
        DiaryItem milk = new DiaryItem(50, "Whole milk", ItemType.DIARY, "Kirkland", 4.0f, 0, 50, 50, asileHashMap.get("refrigirator1"), supplierHashMap.get("supplierPerishable"), LocalDate.parse("2023-01-10"));
        DiaryItem yogurt = new DiaryItem(51, "yogurt", ItemType.DIARY, "Kirkland", 4.0f, 0, 50, 50, asileHashMap.get("refrigirator1"), supplierHashMap.get("supplierPerishable"), LocalDate.parse("2023-12-10"));
        DiaryItem cheese = new DiaryItem(52, "Cheese", ItemType.DIARY, "Kirkland", 4.0f, 0, 50, 50, asileHashMap.get("refrigirator1"), supplierHashMap.get("supplierPerishable"), LocalDate.parse("2023-01-10"));

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
        for (Item item : inventoryArr.values()) {
            LOGGER.debug(item.getItemName() + " " + item.getAsile());
        }
        shop.setInventory(inventory);
        LOGGER.info("Inventory is added to Shop. List of items in inventory");
        inventory.printInventory();
        inventory.addItemToAsile(shop);
        LOGGER.info("Items are added to Asile");
        shop.printItemsInShop();
    }

    private void addPreDefinedCustomers() {
        Customer customer1 = new Customer(7, "person7", "xyz 1234", "1231231234");
        Customer customer2 = new Customer(8, "person8", "abc 1234", "12312312567");
        Customer customer3 = new Customer(9, "person9", "efg 1234", "12312318777");
        Customer customer4 = new Customer(10, "person10", "ght 1234", "12312312766");
        //adding customers to the custom linked list
        shop.addCustomer(customer1);
        shop.addCustomer(customer2);
        shop.addCustomer(customer3);
        shop.addCustomer(customer4);

        customerHashMap.put("customer1", customer1);
        customerHashMap.put("customer2", customer2);
        customerHashMap.put("customer3", customer3);
        customerHashMap.put("customer4", customer4);
        LOGGER.info("Regular customers are added to the shop. ");
    }

    private void addPreDefinedSuppliers() {
        Supplier supplierFruit = new Supplier(1, "SupplierFruit");
        Supplier supplierVeggie = new Supplier(2, "SupplierVeggie");
        Supplier supplierCleaning = new Supplier(3, "SupplierCleaning");
        Supplier supplierPerishable = new Supplier(4, "SupplierPerishable");
        shop.addSupplier(supplierCleaning);
        shop.addSupplier(supplierVeggie);
        shop.addSupplier(supplierFruit);
        shop.addSupplier(supplierPerishable);

        supplierHashMap.put("supplierFruit", supplierFruit);
        supplierHashMap.put("supplierVeggie", supplierVeggie);
        supplierHashMap.put("supplierCleaning", supplierCleaning);
        supplierHashMap.put("supplierPerishable", supplierPerishable);
        LOGGER.info("Suppliers are added to the Shop");
    }

    private void addPreDefinedEmployees() {
        Employee employee1 = new Employee(1, "person1", "xyz 1234", "1231231234", 111, Role.ASSISTANT_STORE_MANAGER, Role.STORE_MANAGER.getBaseSalary());
        Manager manager1 = new Manager(employee1, "store lock \n leave approval");
        Employee employee2 = new Employee(2, "person2", "bc 1234", "12312312567", 222, Role.CASHIER, Role.CASHIER.getBaseSalary(), manager1);
        Employee employee3 = new Employee(3, "person3", "efg 1234", "12312318777", 333, Role.STOCK_CLERK, Role.STOCK_CLERK.getBaseSalary(), manager1);
        Employee employee4 = new Employee(4, "person4", "efg 1234", "12312318777", 333, Role.INVENTORY_CONTROL_SPECIALIST, Role.INVENTORY_CONTROL_SPECIALIST.getBaseSalary(), manager1);
        Employee employee5 = new Employee(5, "perso5", "efg 1234", "12312318777", 333, Role.STOCK_CLERK, Role.STOCK_CLERK.getBaseSalary(), manager1);
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

        managerHashMap.put("manager1", manager1);

        employeeHashMap.put("employee1", employee1);
        employeeHashMap.put("employee2", employee2);
        employeeHashMap.put("employee3", employee3);
        employeeHashMap.put("employee4", employee4);
        employeeHashMap.put("employee5", employee5);
        LOGGER.info("Employees and managers are added to the shop");
    }
}
