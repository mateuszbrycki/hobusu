package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.annotation.BasicAuth;
import factories.TransactionFactory;
import models.Transaction;
import models.TransactionCategory;
import models.TransactionType;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
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
        List<dtos.Transaction> last10transactions = covert(TransactionRepository.findLast10(requestUser));
        JsonNode result = Json.toJson(last10transactions);
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

    private List<dtos.Transaction> covert(List<Transaction> transactions) {
        List<dtos.Transaction> result = new ArrayList<>();

        transactions.forEach(transaction -> result.add(TransactionFactory.create(transaction)));

        return result;
    }
}
