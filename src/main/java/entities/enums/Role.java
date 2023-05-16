package entities.enums;

public enum Role {
    CUSTODIAN("Maintain floors and restrooms", 4, 30000),
    SHOPPING_CART_ATTENDANT("Place the carts back to shop", 4, 20000),
    CASHIER("Serve customers at the Billing Counter", 6, 40000),
    BAGGER("Help cashier with bagging the items", 4, 20000),
    ASSISTANT_STORE_MANAGER("Manage Custodian and shopping cart attendant", 8, 60000),
    STOCK_CLERK("Stock the shelves", 6, 30000),
    INVENTORY_CONTROL_SPECIALIST("Manage Inventory", 6, 50000),
    CUSTOMER_SERVICE_REP("Handle Customer returns, calls", 8, 50000),
    STORE_MANAGER("Manage all the employees of the store", 8, 80000);

    private final String responsibilities;
    private final int minWorkHours;
    private final int baseSalary;

    Role(String responsibilities, int minWorkHours, int baseSalary) {
        this.responsibilities = responsibilities;
        this.minWorkHours = minWorkHours;
        this.baseSalary = baseSalary;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public int getMinWorkHours() {
        return minWorkHours;
    }

    public int getBaseSalary() {
        return baseSalary;
    }
}
