package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import factories.TransactionFactory;
import models.Transaction;
import models.TransactionType;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import repositories.TransactionCategoryRepository;
import repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class TransactionController extends AbstractAuthController {

    public Result list() {
        User requestUser = getRequestUser();
        List<dtos.Transaction> allTransactions = convert(TransactionRepository.findAll(requestUser));
        JsonNode result = Json.toJson(allTransactions);
        return ok(result);
    }

    public Result listForMonth(Long year, Long month) {
        User requestUser = getRequestUser();
        List<dtos.Transaction> transactions = convert(TransactionRepository.findAllForDate(requestUser, year, month));
        JsonNode result = Json.toJson(transactions);
        return ok(result);
    }

    public Result listWithLimit(Long limit) {
        User requestUser = getRequestUser();
        List<dtos.Transaction> limitedTransactions = convert(TransactionRepository.findLast(requestUser, limit));
        JsonNode result = Json.toJson(limitedTransactions);
        return ok(result);
    }


    public Result add() {
        Transaction transaction = prepareRequestObject();

        if(transaction.date == null) {
            transaction.date = LocalDateTime.now();
        }

        if(transaction.type == null) {
            transaction.type = transaction.amount > 0 ? TransactionType.INCOME : TransactionType.OUTCOME;
        }

        User requestUser = getRequestUser();
        //check if user adds transaction to appropriate category
        if(transaction.category.owner.id != requestUser.id) {
            return badRequest();
        }
        transaction.owner = requestUser;
        transaction.creationDate = LocalDateTime.now();

        transaction.save();

        return created(Json.toJson(transaction));
    }

    private Transaction prepareRequestObject() {
        Transaction transaction = Json.fromJson(
                request().body().asJson(),
                Transaction.class
        );

        Long categoryId = request().body().asJson().get("category").asLong();
        transaction.category = TransactionCategoryRepository.findById(categoryId);

        return transaction;
    }

    private List<dtos.Transaction> convert(List<Transaction> transactions) {
        List<dtos.Transaction> result = new ArrayList<>();

        transactions.forEach(transaction -> result.add(TransactionFactory.create(transaction)));

        return result;
    }

    public Result delete(Long id) {
        User requestUser = getRequestUser();
        Transaction transaction = TransactionRepository.findById(id);

        if(transaction.owner.equals(requestUser)) {
            transaction.delete();
            return ok();
        }

        return unauthorized();
    }
}
