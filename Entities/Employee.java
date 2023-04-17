package Entities;
/*
 * Employee class represents all employees of the store.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Employee extends Person{
    protected int employeeId;
    protected String role;
    protected int salary;
    protected Employee manager;


    public Employee(int personId, String name, String address, String phone,
                    int employeeId, String role, int salary) {
        super(personId, name, address, phone);
        this.employeeId = employeeId;
        this.role = role;
        this.salary = salary;
    }

    public Employee(int personId, String name, String address, String phone,
                    int employeeId, String role, int salary, Employee manager) {
        super(personId, name, address, phone);
        this.employeeId = employeeId;
        this.role = role;
        this.salary = salary;
        this.manager = manager;
    }

    public Employee(Employee employee){
        super(employee.getPersonId(), employee.getName(), employee.getAddress(), employee.getPhone());
        this.employeeId = employee.employeeId;
        this.role = employee.role;
        this.salary = employee.salary;
        this.manager = employee.manager;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
