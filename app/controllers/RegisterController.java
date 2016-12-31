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
         user.save();

         return created();
     }

    private User prepareRequestObject() {

        return Json.fromJson(
                request().body().asJson(),
                User.class
        );
    }
}
