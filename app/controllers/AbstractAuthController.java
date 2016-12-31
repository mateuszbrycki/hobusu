package controllers;

import controllers.annotation.BasicAuth;
import models.User;
import play.mvc.Controller;
import service.AuthenticationService;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
@BasicAuth
public class AbstractAuthController extends Controller {

    protected User getRequestUser() {
        String userId = session(AuthenticationService.SESSION_USER_KEY);

        if(userId == null) {
            response().setHeader("Location", "http://localhost:9000");
        }

        return User.findById(userId);
    }
}
