package entities;

import java.util.Calendar;
import java.util.Date;

/*
 * FileTaxes interface is to ensure the shop files taxes every year.
 *
 * @version 1.0 21 Apr 2023
 * @author Ashwini Suresh
 * */

public interface IFileTaxes {
    int filingMonth = 04;
    int filingDay = 15;

    void fileTaxes();
}
