package entities.interfaces;

/*
 * FileTaxes interface is to ensure the shop files taxes every year.
 *
 * @version 1.0 21 Apr 2023
 * @author Ashwini Suresh
 * */

public interface IFileTaxes {
    int FILING_MONTH = 04;
    int FILING_DAY = 15;

    void fileTaxes();
}
