package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.annotation.BasicAuth;
import models.TransactionCategory;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class TransactionCategoryController extends AbstractAuthController{

    public Result list() {
        User requestUser = getRequestUser();
        JsonNode result = Json.toJson(TransactionCategory.findAll(requestUser));
        return ok(result);
    }

    public Result add() {
        TransactionCategory transactionCategory = prepareRequestObject();
        transactionCategory.date = LocalDateTime.now();
        transactionCategory.owner = getRequestUser();
        transactionCategory.save();

        return created(Json.toJson(transactionCategory.id));
    }

    private TransactionCategory prepareRequestObject() {
        TransactionCategory transactionCategory = Json.fromJson(
                request().body().asJson(),
                TransactionCategory.class
        );

        return transactionCategory;
    }
}
