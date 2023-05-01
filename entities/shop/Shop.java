package shop;

import customexceptions.ColdStorageNotWorkingException;
import customexceptions.FreezerAsileNotWorkingException;
import interfaces.IFileTaxes;
import interfaces.IFoodSafetyChecks;
import interfaces.IMaintainColdSection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.Customer;
import people.Employee;
import people.Manager;

import java.util.*;

/*
 * Shop class represents all the entities of the shop.
 *
 * @version 1.0 18 Apr 2023
 * @author Ashwini Suresh
 * */
public final class Shop implements IFoodSafetyChecks, IFileTaxes {
    private final String SHOPNAME;
    private static final Logger LOGGER;
    private ArrayList<Employee> employees;
    private Inventory inventory;
    private HashMap<Integer, Asile> asiles;
    private ArrayList<MarketAsile> markets;
    private ArrayList<FreezerAsile> freezers;
    private ArrayList<RefrigiratorAsile> refrigirators;
    private customlinkedlist.LinkedList<Customer> customers;
    private LinkedList<Supplier> suppliers;
    private TreeSet<BillingCounter> billingCounters;
    private double netGain;
    private static String closedOn;

    static {
        System.out.println("................Welcome.............");
        closedOn = "New Year, Easter, Independence day, Christmas Eve";
        LOGGER = LogManager.getLogger(Shop.class);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Shop(String shopName) {
        this.SHOPNAME = shopName;
        this.employees = new ArrayList<Employee>();
        this.asiles = new HashMap<>();
        this.billingCounters = new TreeSet<>();
        this.customers = new customlinkedlist.LinkedList<>();
        this.freezers = new ArrayList<>();
        this.suppliers = new LinkedList<>();
        this.markets = new ArrayList<>();
        this.inventory = new Inventory();
        this.refrigirators = new ArrayList<>();
    }

    public Shop(String shopName, ArrayList<Employee> employees, Inventory inventory, HashMap<Integer, Asile> asiles, ArrayList<MarketAsile> markets, ArrayList<FreezerAsile> freezers, ArrayList<RefrigiratorAsile> refrigirators, customlinkedlist.LinkedList<Customer> customers, LinkedList<Supplier> suppliers, TreeSet<BillingCounter> billingCounters) {
        this.SHOPNAME = shopName;
        this.employees = employees;
        this.inventory = inventory;
        this.asiles = asiles;
        this.markets = markets;
        this.freezers = freezers;
        this.refrigirators = refrigirators;
        this.customers = customers;
        this.suppliers = suppliers;
        this.billingCounters = billingCounters;
        this.netGain = 0;
    }

    public String getShopName() {
        return SHOPNAME;
    }

    public double getNetGain() {
        return netGain;
    }

    public HashMap<Integer, Asile> getAsiles() {
        return asiles;
    }

    public ArrayList<MarketAsile> getMarkets() {
        return markets;
    }

    public ArrayList<FreezerAsile> getFreezers() {
        return freezers;
    }

    public ArrayList<RefrigiratorAsile> getRefrigirators() {
        return refrigirators;
    }

    public void setNetGain(double netGain) {
        this.netGain = netGain;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void printEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void addAsile(Asile asile) {
        asiles.put(asile.asileNum, asile);
    }

    public void removeAsile(Asile asile) {
        asiles.remove(asile);
    }

    public void addFreezer(FreezerAsile freezer) {
        freezers.add(freezer);
    }

    public void removeFreezer(FreezerAsile freezer) {
        freezers.remove(freezer);
    }

    public void addMarket(MarketAsile market) {
        markets.add(market);
    }

    public void removeMarket(MarketAsile market) {
        markets.remove(market);
    }

    public void addRefrigirator(RefrigiratorAsile refrigirator) {
        refrigirators.add(refrigirator);
    }

    public void removeRefrigirator(RefrigiratorAsile refrigirator) {
        refrigirators.remove(refrigirator);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        LOGGER.info("Customer " + customer + "is removed from the list. ");
    }

    public customlinkedlist.LinkedList<Customer> getCustomers() {
        return customers;
    }


    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }

    public void addBillingCounter(BillingCounter billingCounter) {
        billingCounters.add(billingCounter);
    }

    public TreeSet<BillingCounter> getBillingCounters() {
        return billingCounters;
    }

    public void removeBillingCounter(BillingCounter billingCounter) {
        billingCounters.remove(billingCounter);
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    public void updateNetGain(float amountPaidByCustomer) {
        netGain += amountPaidByCustomer;
    }

    public void placeOrder() {
        LOGGER.info("Order Placed with suppliers");
        for (Supplier supplier : suppliers) {
            supplier.printOrderToFile();
        }
    }

    public void printItemsInShop() {
        LOGGER.info("Shop Name: " + SHOPNAME);
        LOGGER.info("we are open Monday to Saturday 10 AM to 8 PM. Holidays:  " + closedOn);
        LOGGER.info("-----Items in the shop--------");
        for (Asile asile : asiles.values()) {
            for (Item item : asile.getItemsInShelf()) {
                LOGGER.info(item.getItemNo() + " " + item.getItemName());
            }
        }
        LOGGER.info("-----------END--------------");
    }

    public int getTotalReceiptsCount() {
        int total = 0;
        for (BillingCounter billingCounter : billingCounters) {
            total += billingCounter.getReceiptsCount();
        }
        return total;
    }

    //check if food handling is done in all the freezer and perishable section
    @Override
    public boolean hasFoodHandlingProcess() {
        boolean hasFoodHandling = true;
        for (FreezerAsile freezer : freezers) {
            hasFoodHandling = hasFoodHandling && freezer.hasFoodHandlingProcess();
        }
        return hasFoodHandling;
    }

    //ask inventory to check through all items for expiry
    @Override
    public void removePerishableBeforeExpiry() {
        for (Asile asile : asiles.values()) {
            if (asile.getClass() == FreezerAsile.class) {
                ((FreezerAsile) asile).removePerishableBeforeExpiry();
            } else if (asile.getClass() == RefrigiratorAsile.class) {
                ((RefrigiratorAsile) asile).removePerishableBeforeExpiry();
            }
        }
    }

    @Override
    public void fileTaxes() {
        if (Calendar.getInstance().get(Calendar.MONTH) - IFileTaxes.FILING_MONTH < 2) {
            if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - IFileTaxes.FILING_DAY < 10) {
                LOGGER.warn("we are running out of time. Hurry up!");
            }
            LOGGER.info("filing tax for this year");
        }
    }

    //check for all freezer temperature. If the freezer is not working, then replace with a new freezer.
    public void maintainTempForAllFreezer() {
        LOGGER.debug("in maintainTempForAllFreezer");
        for (IMaintainColdSection maintainColdSection : freezers) {
            try {
                maintainColdSection.maintainTemp();
            } catch (FreezerAsileNotWorkingException exception) {
                LOGGER.debug("in maintainTempForAllFreezer catch");
                LOGGER.error(exception.getMessage());
                FreezerAsile oldFreezerAsile = (FreezerAsile) maintainColdSection;
                try {
                    FreezerAsile newFreezerasile = oldFreezerAsile.clone();
                    freezers.add(newFreezerasile);
                    freezers.remove(oldFreezerAsile);
                    LOGGER.info("The freezer is replaced");
                } catch (CloneNotSupportedException cloneNotSupportedException) {
                    LOGGER.error("The freezer asile " + oldFreezerAsile.asileNum + " could not be replaced");
                }
            } catch (ColdStorageNotWorkingException exception) {
                LOGGER.info(exception.getMessage());
            }
        }
    }

    //Manager will increase the salary of employee by said percentage
    public void increaseSalaryForEmployees(float percentage) {
        for (Employee employee : employees) {
            if (employee.getManager() != null) {
                employee.getManager().salaryHike(employee, percentage);
            } else {
                employee.setSalary(employee.getSalary() + (employee.getSalary() * percentage));
            }
        }
    }

    // Manager is asked if the employees under them have min wage salary.
    public void isMinWageMetForEmployeesUnder(Manager manager) {
        manager.isMinWageMetForEmployees();
        LOGGER.info("Min wage check is completed by : " + manager);
    }
}
