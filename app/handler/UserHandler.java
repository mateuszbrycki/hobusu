package handler;

import models.User;
import org.springframework.context.annotation.Bean;

import javax.inject.Scope;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
//TODO mbrycki separate handlers by session
public class UserHandler {

    private static UserHandler instance = null;
    private User user = null;


    private UserHandler(User user) {
        this.user = user;
    }

    public static User registerUser(User user) {
        if(instance == null) {
            instance = new UserHandler(user);
        }

        return instance.getUser();
    }

    public static User getUser() {
        return instance.user;
    }
}
