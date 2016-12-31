package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
@Entity
public class User extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    @Column(unique = true)
    public String mail;

    @NotNull
    public String password;

    private static Model.Finder<String, User> find = new Model.Finder<String, User>(User.class);

    public User() {
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public static User findByMailAndPassword(String mail, String password) {
        return find.where().eq("mail", mail).eq("password", password).findUnique();
    }

    public static User findById(String userId) {
        return find.byId(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
