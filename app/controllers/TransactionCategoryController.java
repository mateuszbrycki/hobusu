package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.annotation.BasicAuth;
import factories.TransactionCategoryFactory;
import models.TransactionCategory;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import repositories.TransactionCategoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class TransactionCategoryController extends AbstractAuthController{

    public Result list() {
        User requestUser = getRequestUser();
        List<dtos.TransactionCategory> categoriesList = convert(TransactionCategoryRepository.findAll(requestUser));
        JsonNode result = Json.toJson(categoriesList);
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

    private List<dtos.TransactionCategory> convert(List<TransactionCategory> categories) {
        List<dtos.TransactionCategory> result = new ArrayList<>();
        categories.forEach(category -> result.add(TransactionCategoryFactory.create(category)));
        return result;
    }
}
