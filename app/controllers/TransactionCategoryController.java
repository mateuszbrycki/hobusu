package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.TransactionCategory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class TransactionCategoryController extends Controller{

    public Result list() {
        JsonNode result = Json.toJson(TransactionCategory.findAll());
        return ok(result);
    }

    public Result add() {
        TransactionCategory transactionCategory = prepareRequestObject();
        transactionCategory.date = LocalDateTime.now();
        transactionCategory.save();

        return created();
    }

    private TransactionCategory prepareRequestObject() {
        TransactionCategory transactionCategory = Json.fromJson(
                request().body().asJson(),
                TransactionCategory.class
        );

        return transactionCategory;
    }
}
