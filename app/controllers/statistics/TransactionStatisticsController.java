package controllers.statistics;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.AbstractAuthController;
import dtos.CategorySummary;
import factories.TransactionFactory;
import models.Transaction;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import repositories.TransactionRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mateusz Brycki on 17/02/2017.
 */
public class TransactionStatisticsController extends AbstractAuthController {

    @Inject
    TransactionRepository transactionRepository;

    public Result mostExpensiveTransactions() {
        User requestUser = getRequestUser();
        Collection<Transaction> transactions = transactionRepository.findMostExpensiveTransactions(requestUser);

        JsonNode result = Json.toJson(convert(transactions));
        return ok(result);
    }

    private List<dtos.Transaction> convert(Collection<Transaction> transactions) {
        List<dtos.Transaction> result = new ArrayList<>();

        transactions.forEach(transaction -> result.add(TransactionFactory.create(transaction)));

        return result;
    }
}
