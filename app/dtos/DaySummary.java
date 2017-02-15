package dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class DaySummary {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate day;
    private Double amount;

    public DaySummary(LocalDate day, Double amount) {
        this.day = day;
        this.amount = amount;
    }

    public LocalDate getDay() {
        return day;
    }

    public Double getAmount() {
        BigDecimal bd = new BigDecimal(amount).setScale(2, RoundingMode.FLOOR);
        return bd.doubleValue();
    }
}
