package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Transaction;
import models.TransactionCategory;
import models.TransactionType;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.time.LocalDateTime;


/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class TransactionController extends Controller {

    public Result list() {
        JsonNode result = Json.toJson(Transaction.findAll());
        return ok(result);
    }


    public Result add() {
        Transaction transaction = prepareRequestObject();

        transaction.date = LocalDateTime.now();
        transaction.type = transaction.amount > 0 ? TransactionType.INCOME : TransactionType.OUTCOME;
        transaction.save();

        return created();
    }

    public Result generate() {

        new Transaction(30., LocalDateTime.now(), TransactionType.INCOME, TransactionCategory.findById(1L)).save();
        new Transaction(-30., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategory.findById(2L)).save();
        new Transaction(1000., LocalDateTime.now(), TransactionType.INCOME, TransactionCategory.findById(3L)).save();
        new Transaction(-25., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategory.findById(4L)).save();

        return ok("Data generated");
    }

    private Transaction prepareRequestObject() {
        Transaction transaction = Json.fromJson(
                request().body().asJson(),
                Transaction.class
        );

        Long categoryId = request().body().asJson().get("category").asLong();
        transaction.category = TransactionCategory.findById(categoryId);

        return transaction;
    }
}
