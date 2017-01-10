package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.annotation.BasicAuth;
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


/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class TransactionController extends AbstractAuthController {

    public Result list() {
        User requestUser = getRequestUser();
        JsonNode result = Json.toJson(TransactionRepository.findAll(requestUser));
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
}
