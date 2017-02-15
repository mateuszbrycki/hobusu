package dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Mateusz Brycki on 15/02/2017.
 */
public class MonthSummary {

    private String month;
    private Double amount;

    public MonthSummary(String month, Double amount) {
        this.month = month;
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public Double getAmount() {

        BigDecimal bd = new BigDecimal(amount).setScale(2, RoundingMode.FLOOR);
        return bd.doubleValue();
    }
}
