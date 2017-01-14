package dtos;

import dtos.*;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class CategorySummary {

    public TransactionCategory category;
    public Double amount;

    public CategorySummary(TransactionCategory category, Double amount) {
        this.category = category;
        this.amount = amount;
    }
}
