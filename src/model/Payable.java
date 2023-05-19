package model;

import java.util.Calendar;

public interface Payable {
    Calendar transactionDate();
    double totalValue();
}
