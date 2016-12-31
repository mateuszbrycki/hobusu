package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.annotation.BasicAuth;
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
@BasicAuth
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
