package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dtos.CategorySummary;
import factories.TransactionCategoryFactory;
import models.TransactionCategory;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import services.StatisticsService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Brycki on 13/01/2017.
 */
public class StatisticsController extends AbstractAuthController {

    @Inject
    StatisticsService statisticsService;


    public Result summarizeCategoryMonthly() {
        User requestUser = getRequestUser();
        List<CategorySummary> monthCategorySummary = convert(statisticsService.summarizeCategoryMonthly(requestUser));
        JsonNode result = Json.toJson(monthCategorySummary);
        return ok(result);
    }

    public Result accountBalanceMonthly() {
        User requestUser = getRequestUser();
        JsonNode result = Json.toJson(statisticsService.accountBalanceMonthly(requestUser));
        return ok(result);
    }

    private List<CategorySummary> convert(Map<TransactionCategory, Double> transactionCategoryDoubleMap) {
        List<CategorySummary> result = new ArrayList<>();

        for(Map.Entry entry: transactionCategoryDoubleMap.entrySet()) {

            CategorySummary categorySummary = new CategorySummary(
                    TransactionCategoryFactory.create((TransactionCategory)entry.getKey()),
                    (Double)entry.getValue()
            );

            result.add(categorySummary);
        }

        return result;
    }
}
