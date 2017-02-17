package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dtos.AvailableMonthYear;
import dtos.Transaction;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import repositories.TransactionRepository;
import services.TransactionDateService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mateusz Brycki on 17/02/2017.
 */
public class DateController extends AbstractAuthController {

    @Inject
    private TransactionDateService transactionDateService;

    public Result getAvailableMonthsAndYears() {
        User requestUser = getRequestUser();
        Collection<AvailableMonthYear> availableMonthsAndYears = transactionDateService.getAvailableMonthsAndYears(requestUser);

        JsonNode result = Json.toJson(availableMonthsAndYears);
        return ok(result);
    }

}
