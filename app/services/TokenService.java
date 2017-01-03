package services;

import models.Token;
import models.User;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
public class TokenService {

    public Token createToken(User user) {
        Token token = new Token();
        token.user = user;
        token.token = UUID.randomUUID().toString();
        token.date = LocalDateTime.now();
        token.save();

        return token;
    }

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
