package services;

import javax.inject.Inject;

import models.Transaction;
import models.TransactionCategory;
import models.User;
import repositories.TransactionRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 13/01/2017.
 */
public class StatisticsService {

    @Inject
    private TransactionRepository transactionRepository;

    public Map<String, Double> summarizeCategoryMonthly(User user) {

        List<Transaction> transactions = transactionRepository.findFromCurrentMonth(user);

        Map<String, Double> result = new HashMap<>();

        transactions.forEach(transaction -> {
            String categoryName = transaction.getCategory().getName();

            Double fromMap = result.get(categoryName);
            Double summary = fromMap != null ? result.get(categoryName) : 0d;
            summary = summary + transaction.amount;

            result.put(categoryName, summary);
        });

        return result;
    }
}
