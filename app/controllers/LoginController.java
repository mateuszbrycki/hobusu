package controllers;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import controllers.annotation.BasicAuth;
import handler.UserHandler;
import models.Token;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 *
 * Controller provides logic to login and logout.
 */
public class LoginController extends Controller {

    /**
     * Credentials passed by user in the login form
     * @return information about logged users token - token should be validated on each call to controllers
     */
    public Result login() {

        User requestObject = prepareRequestObject();
        User user = User.findByMailAndPassword(requestObject.mail, requestObject.password);

        if(user != null) {
            Token token = new Token();
            token.user = user;
            token.token = UUID.randomUUID().toString();
            token.date = LocalDateTime.now();
            token.save();

            UserHandler.registerUser(user);

            return created(token.token);
        }

        return badRequest();
    }

    /**
     *
     * @return default token for guest/not logged user
     */
    @BasicAuth
    public Result logout() {

        String requestToken = request().getHeader(AUTHORIZATION);
        Token token = Token.findByValue(requestToken);
        token.delete();
        session().clear();

        return ok("Logged  out");
    }

    private User prepareRequestObject() {

        return Json.fromJson(
                request().body().asJson(),
                User.class
        );
    }
}
