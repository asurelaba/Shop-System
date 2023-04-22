package entities;

/*
 * ISalary interface is to ensure the Employee  has salary hikes.
 *
 * @version 1.0 21 Apr 2023
 * @author Ashwini Suresh
 * */
public interface ISalary {
    int minWagePerHour = 15;

    void salaryHike(float percentage);

    boolean isMinWageMet();
}
