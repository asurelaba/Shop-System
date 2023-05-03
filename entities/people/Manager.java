package people;

/*
 * Manager class represents manager with their special permissions.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

import customexceptions.MinWageNotMetException;
import interfaces.ISalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Manager extends Employee implements ISalary {
    private static final Logger LOGGER = LogManager.getLogger(Manager.class);
    private String permissions;
    private ArrayList<Employee> employees;

    public Manager(Employee employee, String permissions) {
        super(employee);
        this.permissions = permissions;
        this.employees = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Override
    public void printDetails() {
        System.out.println("Employee Id:" + employeeId + "\n Name: " + name + "\nAddress: " + address + "\nPhone: " + phone + "\n Role: " + role + "\n Manager" + manager + "\n permissions" + permissions);
    }

    @Override
    public void salaryHike(Employee employee, float percentage) {
        salary += (salary * percentage);
        employee.setSalary(employee.getSalary() * percentage);
    }

    @Override
    public void isMinWageMetForEmployees() {
        for (Employee employee : employees) {
            try {
                if (employee.getSalary() < ISalary.MIN_WAGE_PER_HOUR) {
                    throw new MinWageNotMetException("Min wage is not met for " + employee.name + ".");
                }
            } catch (MinWageNotMetException minWageNotMetException) {
                LOGGER.warn(minWageNotMetException.getMessage() + " Increasing the salary...");
                employee.setSalary(ISalary.MIN_WAGE_PER_HOUR);
            }
        }
    }

    public void checkEmployeeWorkingHours(){
        for (Employee employee: employees) {
            employee.checkWorkingHours();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
