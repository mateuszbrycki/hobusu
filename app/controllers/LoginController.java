package controllers;

import controllers.annotation.BasicAuth;
import models.Token;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.UserRepository;
import services.AuthenticationService;

import javax.inject.Inject;
/**
 * Created by Mateusz Brycki on 30/12/2016.
 *
 * Controller provides logic to login and logout.
 */
public class LoginController extends Controller {

    @Inject
    private AuthenticationService authenticationService;

    /**
     * Credentials passed by user in the login form
     * @return information about logged users token - token should be validated on each call to controllers
     */
    public Result login() {

        User requestObject = prepareRequestObject();
        User user = UserRepository.findByMailAndPassword(requestObject.mail, requestObject.password);

        if(user != null) {
            Token token = authenticationService.login(user);
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
        authenticationService.logout(session(), requestToken);

        return ok(Token.DEFAULT_TOKEN_VALUE);
    }

    private User prepareRequestObject() {
        return Json.fromJson(
                request().body().asJson(),
                User.class
        );
    }
}
