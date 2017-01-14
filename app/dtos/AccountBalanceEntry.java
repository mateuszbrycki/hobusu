package dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class AccountBalanceEntry {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate day;
    private Double amount;

    public AccountBalanceEntry(LocalDate day, Double amount) {
        this.day = day;
        this.amount = amount;
    }

    public LocalDate getDay() {
        return day;
    }

    public Double getAmount() {
        return amount;
    }
}
