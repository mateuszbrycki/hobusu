package controllers;

import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class RegisterController extends Controller {

     public Result register() {
         User user = prepareRequestObject();

         if(User.isUnique(user)) {
             user.save();
             return created();
         }

         return badRequest("Users data is not unique!");

     }

    private User prepareRequestObject() {

        return Json.fromJson(
                request().body().asJson(),
                User.class
        );
    }
}
