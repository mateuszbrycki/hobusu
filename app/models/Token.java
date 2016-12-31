package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
@Entity
public class Token extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    public LocalDateTime date;

    @NotNull
    public String token;

    @ManyToOne
    public User user;

    private static Model.Finder<String, Token> find = new Model.Finder<String, Token>(Token.class);

    public static Token findForUser(User user) {
        return find.where().eq("user.id", user.id).findUnique();
    }

    public static Token findByValue(String token) {
        return find.where().eq("token", token).orderBy("id").findUnique();
    }

}
