package entities;

/*
 * Customer class represents customers of the shop and their shopping receipt.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Customer extends Person{
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
    public void printDetails(){
        System.out.println("Customer Name: " + name);
        System.out.println("latest Receipt : " + receipt);
    }
}
