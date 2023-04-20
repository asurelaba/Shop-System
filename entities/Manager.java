package entities;
/*
 * Manager class represents manager with their special permissions.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Manager extends Employee {
    private String permissions;

    public Manager(Employee employee, String permissions) {
        super(employee);
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
