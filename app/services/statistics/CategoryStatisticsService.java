package services.statistics;

import dtos.CategorySummary;
import factories.TransactionCategoryFactory;
import models.Transaction;
import models.TransactionCategory;
import models.User;
import repositories.TransactionRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 15/02/2017.
 */
public class CategoryStatisticsService {

    @Inject
    private TransactionRepository transactionRepository;

    public Collection<CategorySummary> summarizeOutcomeByCategoryAndMonth(User user) {

        List<Transaction> transactions = transactionRepository.findFromCurrentMonth(user);

        Map<TransactionCategory, Double> result = transactions.stream().collect(
                Collectors.toMap(t -> t.category, Transaction::getAmount, (first, second) -> first + second)
        );

        return convertToCategorySummaryCollection(result);
    }

    private Collection<CategorySummary> convertToCategorySummaryCollection(Map<TransactionCategory, Double> transactionCategoryDoubleMap) {
        List<CategorySummary> result = new ArrayList<>();

        for(Map.Entry entry: transactionCategoryDoubleMap.entrySet()) {

            CategorySummary categorySummary = new CategorySummary(
                    TransactionCategoryFactory.create((TransactionCategory)entry.getKey()),
                    (Double)entry.getValue()
            );

            result.add(categorySummary);
        }

        return result;
    }
}
