package entities;

import java.util.ArrayList;

/*
 * Shop class represents all the entities of the shop.
 *
 * @version 1.0 18 Apr 2023
 * @author Ashwini Suresh
 * */
public class Shop {

    private String shopName;
    private ArrayList<Employee> employees;
    private Inventory inventory;
    private ArrayList<Asile> asiles;
    private ArrayList<MarketAsile> markets;
    private ArrayList<FreezerAsile> freezers;
    private ArrayList<RefrigiratorAsile> refrigirators;
    private ArrayList<Customer> customers;
    private ArrayList<Supplier> suppliers;
    private ArrayList<BillingCounter> billingCounters;

    private double netGain;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Shop(String shopName) {
        this.shopName = shopName;
        this.employees = new ArrayList<Employee>();
        this.asiles = new ArrayList<Asile>();
        this.billingCounters = new ArrayList<BillingCounter>();
        this.customers = new ArrayList<Customer>();
        this.freezers = new ArrayList<FreezerAsile>();
        this.suppliers = new ArrayList<Supplier>();
        this.markets = new ArrayList<MarketAsile>();
        this.inventory = new Inventory();
        this.refrigirators = new ArrayList<RefrigiratorAsile>();
    }

    public Shop(ArrayList<Employee> employees, Inventory inventory, ArrayList<Asile> asiles,
                ArrayList<MarketAsile> markets, ArrayList<FreezerAsile> freezers,
                ArrayList<RefrigiratorAsile> refrigirators,
                ArrayList<Customer> customers, ArrayList<Supplier> suppliers,
                ArrayList<BillingCounter> billingCounters) {
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
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getNetGain() {
        return netGain;
    }

    public void setNetGain(double netGain) {
        this.netGain = netGain;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
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
        asiles.add(asile);
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

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }

    public void addBillingCounter(BillingCounter billingCounter) {
        billingCounters.add(billingCounter);
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
        System.out.println("Order Placed");
    }

}
