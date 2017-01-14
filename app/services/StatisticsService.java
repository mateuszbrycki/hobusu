package services;

import javax.inject.Inject;

import dtos.AccountBalanceEntry;
import models.Transaction;
import models.TransactionCategory;
import models.User;
import org.springframework.cglib.core.Local;
import repositories.TransactionRepository;
import utils.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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

        Map<String, Double> result = transactions.stream().collect(
                Collectors.toMap(t -> t.getCategory().getName(), Transaction::getAmount, (first, second) -> first + second)
        );

        return result;
    }

    public List<AccountBalanceEntry> accountBalanceMonthly(User user) {

        List<Transaction> transactions = transactionRepository.findFromCurrentMonth(user);

        Map<String, Double> summarizedValues = summarizeTransactionsData(transactions);

        return convertSummarizedToList(summarizedValues);
    }

    private Map<String, Double> summarizeTransactionsData(List<Transaction> transactions) {
        LocalDateTime todayDate = LocalDateTime.now();
        YearMonth yearMonth = YearMonth.of(todayDate.getYear(), todayDate.getMonth());
        Integer monthDays = yearMonth.lengthOfMonth();

        Map<String, Double> summarizedValues = transactions.stream().collect(
                Collectors.toMap(t -> DateUtil.dateToCommonFormat(t.getDate()), Transaction::getAmount, (first, second) -> first + second)
        );

        return summarizedValues;
    }


    private List<AccountBalanceEntry> convertSummarizedToList(Map<String, Double> summarizedValues) {
        List<AccountBalanceEntry> result = new ArrayList<>(summarizedValues.size());

        for(Map.Entry<String, Double> entry : summarizedValues.entrySet()) {
            result.add(new AccountBalanceEntry(LocalDate.parse(entry.getKey()), entry.getValue()));
        }

        Collections.sort(result, new Comparator<AccountBalanceEntry>() {
            @Override
            public int compare(AccountBalanceEntry o1, AccountBalanceEntry o2) {
                return o1.getDay().compareTo(o2.getDay());
            }
        });

        return result;
    }
}
