package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import handler.UserHandler;
import models.Transaction;
import models.TransactionCategory;
import models.TransactionType;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
public class AppController extends Controller{

    public Result generate() {

        new Transaction(30., LocalDateTime.now(), TransactionType.INCOME, TransactionCategory.findById(1L)).save();
        new Transaction(-30., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategory.findById(2L)).save();
        new Transaction(1000., LocalDateTime.now(), TransactionType.INCOME, TransactionCategory.findById(3L)).save();
        new Transaction(-25., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategory.findById(4L)).save();

        return ok(UserHandler.getUser().toString());
    }
}
