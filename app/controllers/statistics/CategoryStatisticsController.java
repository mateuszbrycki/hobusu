package controllers.statistics;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.AbstractAuthController;
import dtos.CategorySummary;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import services.statistics.CategoryStatisticsService;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mateusz Brycki on 15/02/2017.
 */
public class CategoryStatisticsController extends AbstractAuthController {

    @Inject
    CategoryStatisticsService statisticsService;

    public Result summarizeCategoryMonthly() {
        User requestUser = getRequestUser();
        Collection<CategorySummary> monthCategorySummary = statisticsService.summarizeOutcomeByCategoryAndMonth(requestUser);

        JsonNode result = Json.toJson(monthCategorySummary);
        return ok(result);
    }
}
