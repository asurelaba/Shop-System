package Entities;

/*
 * Customer class represents customers of the shop and their shopping receipt.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Customer {
    private Person person;
    private Receipt receipt;

    public Customer(Person person, Receipt receipt) {
        this.person = person;
        this.receipt = receipt;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
