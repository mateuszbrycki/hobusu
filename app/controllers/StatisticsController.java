package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import services.StatisticsService;

import javax.inject.Inject;

/**
 * Created by Mateusz Brycki on 13/01/2017.
 */
public class StatisticsController extends AbstractAuthController {

    @Inject
    StatisticsService statisticsService;


    public Result summarizeCategoryMonthly() {
        User requestUser = getRequestUser();
        JsonNode result = Json.toJson(statisticsService.summarizeCategoryMonthly(requestUser));
        return ok(result);
    }

    public Result accountBalanceMonthly() {
        User requestUser = getRequestUser();
        JsonNode result = Json.toJson(statisticsService.accountBalanceMonthly(requestUser));
        return ok(result);
    }
}
