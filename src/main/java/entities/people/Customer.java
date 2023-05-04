package entities.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entities.shop.Receipt;

/*
 * Customer class represents customers of the shop and their shopping receipt.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Customer extends Person {
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    private Receipt receipt;

    public Customer(int personId, String name, String address, String phone, Receipt receipt) {
        super(personId, name, address, phone);
        this.receipt = receipt;
    }

    public Customer(int personId, String name, String address, String phone) {
        super(personId, name, address, phone);
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    @Override
    public void printDetails() {
        LOGGER.info("Customer Name: " + name);
        LOGGER.info("latest Receipt : " + receipt);
    }

    @Override
    public String toString() {
        return "Customer{" + "personId=" + personId + ", name='" + name + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + '}';
    }
}
