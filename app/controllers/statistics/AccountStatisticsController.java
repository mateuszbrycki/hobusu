package controllers.statistics;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.AbstractAuthController;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import services.statistics.AccountStatisticsService;

import javax.inject.Inject;

/**
 * Created by Mateusz Brycki on 15/02/2017.
 */
public class AccountStatisticsController extends AbstractAuthController {

    @Inject
    AccountStatisticsService statisticsService;

    public Result accountSummaryDaily() {
        User requestUser = getRequestUser();
        JsonNode result = Json.toJson(statisticsService.summarizeOutcomeByDay(requestUser));
        return ok(result);
    }

    public Result accountSummaryMonthly() {
        User requestUser = getRequestUser();
        JsonNode result = Json.toJson(statisticsService.summarizeOutcomeByMonth(requestUser));
        return ok(result);
    }
}
