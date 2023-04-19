package entities;
/*
 * Person class represents all personal of the store including customers, employee.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Person {
    protected int personId;
    protected String name;
    protected String address;
    protected String phone;

    public Person(int personId, String name, String address, String phone) {
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
