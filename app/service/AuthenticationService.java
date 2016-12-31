package service;

import models.Token;
import models.User;
import play.mvc.Http;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
@Singleton
public class AuthenticationService {

    public static final String SESSION_USER_KEY = "user_id";

    @Inject
    private TokenService tokenService;

    public Boolean isAuthorized(Http.Context context, String token) {

        if(this.tokenService.validateToken(token)) {
            User tokenOwner = Token.findByValue(token).user;
            context.session().put(SESSION_USER_KEY, tokenOwner.id.toString());

            return true;
        }

        return false;
    }

    public Token login(User user) {
        Token token = tokenService.createToken(user);

        return token;
    }

    public void logout(Http.Session session, String requestToken) {
        Token token = Token.findByValue(requestToken);
        token.delete();
        session.clear();
    }

}
