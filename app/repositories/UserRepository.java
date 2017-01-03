package repositories;

import com.avaje.ebean.Model;
import models.User;

import java.util.List;

/**
 * Created by Mateusz Brycki on 03/01/2017.
 */
public class UserRepository {

    private static Model.Finder<String, User> find = new Model.Finder<String, User>(User.class);


    public static User findByMailAndPassword(String mail, String password) {
        return find.where().eq("mail", mail).eq("password", password).findUnique();
    }

    public static User findById(String userId) {
        return find.byId(userId);
    }

    //TODO mbrycki consider change return type
    public static List<User> findByMail(String mail) {
        return find.where().eq("mail", mail).findList();
    }
}
