package service;

import models.Token;

import javax.inject.Singleton;
import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
@Singleton
public class AuthenticationService {

    public Boolean validateToken(String requestToken) {

        Token token = Token.findByValue(requestToken);
        if(token == null) {
            return false;
        }

        //check if token is still valid
        return this.checkIfTokenDateIsValid(token);

    }

    private Boolean checkIfTokenDateIsValid(Token token) {

        LocalDateTime tokenDate = token.date;

        //check if token is not older than 3 days
        //now - 3 days should be before token creation date
        return  LocalDateTime.now().minusDays(3).isBefore(tokenDate);
    }
}
