package interfaces;

import people.Employee;

/*
 * ISalary interface is to ensure the Employee  has salary hikes.
 *
 * @version 1.0 21 Apr 2023
 * @author Ashwini Suresh
 * */
public interface ISalary {
    int MIN_WAGE_PER_HOUR = 15;

    void salaryHike(Employee employee, float percentage);

    void isMinWageMetForEmployees();
}
