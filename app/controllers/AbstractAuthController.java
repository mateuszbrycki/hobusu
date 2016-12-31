package controllers;

import controllers.annotation.BasicAuth;
import models.User;
import play.mvc.Controller;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
@BasicAuth
public class AbstractAuthController extends Controller {

    protected User getRequestUser() {
        String userId = session("userId");

        if(userId == null) {
            response().setHeader("Location", "http://localhost:9000");
        }

        return User.findById(userId);
    }
}
