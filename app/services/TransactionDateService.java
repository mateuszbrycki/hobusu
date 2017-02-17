package services;

import dtos.AvailableMonthYear;
import models.Transaction;
import models.User;
import repositories.TransactionRepository;

import javax.inject.Inject;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Mateusz Brycki on 17/02/2017.
 */
public class TransactionDateService {

    @Inject
    private TransactionRepository transactionRepository;

    public Collection<AvailableMonthYear> getAvailableMonthsAndYears(User user) {
        Collection<Transaction> transactions = transactionRepository.findAll(user);

        Collection<AvailableMonthYear> result = new HashSet<>();
        transactions.forEach(transaction -> result.add(
                new AvailableMonthYear(
                        YearMonth.of(transaction.getDate().getYear(), transaction.getDate().getMonth()))
                )
        );

        return result;
    }
}
