package entities.people;

import entities.interfaces.IWorkingShift;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Date;

/*
 * Employee class represents all employees of the store.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */
public class Employee extends Person implements IWorkingShift {
    private static final Logger LOGGER = LogManager.getLogger(Employee.class);
    protected static byte workHours;
    protected int employeeId;
    protected String role;
    protected float salary;
    protected Manager manager;
    protected Date shiftStartTime;
    protected Date shiftEndTime;

    static {
        workHours = 8;
    }


    public Employee(int personId, String name, String address, String phone, int employeeId, String role, float salary) {
        super(personId, name, address, phone);
        this.employeeId = employeeId;
        this.role = role;
        this.salary = salary;
    }

    public Employee(int personId, String name, String address, String phone, int employeeId, String role, float salary, Manager manager) {
        super(personId, name, address, phone);
        this.employeeId = employeeId;
        this.role = role;
        this.salary = salary;
        this.manager = manager;
        manager.getEmployees().add(this);
    }

    public Employee(Employee employee) {
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

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Date getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Date shiftStartTime) {
        LOGGER.debug("Shift start time set");
        this.shiftStartTime = shiftStartTime;
    }

    public Date getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Date shiftEndTime) {
        LOGGER.debug("Shift end time set");
        this.shiftEndTime = shiftEndTime;
    }

    public int getWorkedHoursForTheDay() {
        return shiftEndTime.compareTo(shiftStartTime);
    }

    @Override
    public void printDetails() {
        System.out.println("Employee Id:" + employeeId + "\n Name: " + name + "\nAddress: " + address + "\nPhone: " + phone + "\n Role: " + role);
        if (manager != null) {
            System.out.println("Manager: " + manager);
        }
    }

    @Override
    public void setWorkingHours() {
        LOGGER.info("The employee cannot work for more than " + workHours + " hrs");
    }

    public void checkWorkingHours() {
        if (getWorkedHoursForTheDay() > 8) {
            LOGGER.info("Employee " + name + "(" + employeeId + ") is over worked!!!!");
        }
    }
}
