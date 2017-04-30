package services.statistics;

import dtos.DaySummary;
import dtos.MonthSummary;
import models.Transaction;
import models.User;
import repositories.TransactionRepository;
import utils.DateUtil;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 15/02/2017.
 */
public class AccountStatisticsService {

    @Inject
    private TransactionRepository transactionRepository;

    public Collection<DaySummary> summarizeOutcomeByDay(User user) {

        List<Transaction> transactions = transactionRepository.findFromCurrentMonth(user);

        Map<String, Double> summarizedValues = transactions.stream().collect(
                Collectors.toMap(t -> DateUtil.dateToCommonFormat(t.getDate()), Transaction::getAmount, (first, second) -> first + second)
        );

        return convertSummarizedToDaySummary(summarizedValues);
    }

    public Collection<MonthSummary> summarizeOutcomeByMonth(User user) {

        List<Transaction> transactions = transactionRepository.findAll(user);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        Map<String, Double> result = transactions.stream().collect(
                Collectors.toMap(t -> t.getDate().format(formatter).toString(), Transaction::getAmount, (first, second) -> first + second)
        );

        return convertSummarizedToMonthSummary(result);
    }

    private Collection<DaySummary> convertSummarizedToDaySummary(Map<String, Double> summarizedValues) {
        List<DaySummary> result = new ArrayList<>(summarizedValues.size());

        summarizedValues.entrySet().forEach(entry ->
                result.add(new DaySummary(LocalDate.parse(entry.getKey()), entry.getValue())));

        Collections.sort(result, Comparator.comparing(DaySummary::getDay));

        return result;
    }

    private Collection<MonthSummary> convertSummarizedToMonthSummary(Map<String, Double> input) {
        List<MonthSummary> result = new ArrayList<>(input.size());

        input.entrySet().forEach(element ->
                result.add(new MonthSummary(element.getKey(), element.getValue()))
        );

        Collections.sort(result, Comparator.comparing(MonthSummary::getMonth));
        return result;
    }
}
