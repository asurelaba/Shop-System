package entities;
/*
 * Manager class represents manager with their special permissions.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class Manager extends Employee implements ISalary {
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

    @Override
    public void printDetails() {
        System.out.println("Employee Id:" + employeeId + "\n Name: " + name + "\nAddress: " + address + "\nPhone: "
                + phone + "\n Role: " + role + "\n Manager" + manager + "\n permissions" + permissions);
    }

    @Override
    public void salaryHike(Employee employee, float percentage) {
        salary += (salary * percentage);
        employee.setSalary(employee.getSalary() * percentage);
    }

    @Override
    public boolean isMinWageMet(Employee employee) {
        return (employee.getSalary() > ISalary.MIN_WAGE_PER_HOUR) ? true : false;
    }

    @Override
    public String toString() {
        return name;
    }
}
