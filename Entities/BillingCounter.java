package Entities;
/*
 * BillingCounter class represents checkout counters.
 *
 * @version 1.0 17 Apr 2023
 * @author Ashwini Suresh
 * */

public class BillingCounter {

    private int counterNum;
    private Employee employee;

    public BillingCounter(int counterNum, Employee employee) {
        this.counterNum = counterNum;
        this.employee = employee;
    }

    public int getCounterNum() {
        return counterNum;
    }

    public void setCounterNum(int counterNum) {
        this.counterNum = counterNum;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
