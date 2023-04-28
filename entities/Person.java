/*
 * Person class represents all personal of the store including customers, employee.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

import java.util.Objects;

public abstract class Person {
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

    public abstract void printDetails();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && Objects.equals(name, person.name) && Objects.equals(phone, person.phone);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(personId, name, phone);
    }
}
