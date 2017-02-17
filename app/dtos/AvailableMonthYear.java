package dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.YearMonth;

/**
 * Created by Mateusz Brycki on 17/02/2017.
 */
public class AvailableMonthYear {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM")
    private YearMonth yearMonth;

    public AvailableMonthYear(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableMonthYear that = (AvailableMonthYear) o;

        return yearMonth != null ? yearMonth.equals(that.yearMonth) : that.yearMonth == null;

    }

    @Override
    public int hashCode() {
        return yearMonth != null ? yearMonth.hashCode() : 0;
    }
}
