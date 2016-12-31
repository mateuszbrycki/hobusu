package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import handler.UserHandler;
import models.Transaction;
import models.TransactionCategory;
import models.TransactionType;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
public class AppController extends Controller{

    public Result generate() {

        return ok("Database is ready!");
    }
}
