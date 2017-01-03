package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import repositories.UserRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

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


    public User() {
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public Boolean isUnique() {

        List<User> usersWithSameMails = UserRepository.findByMail(this.mail);
        if(usersWithSameMails.size() != 0) {
            return false;
        }

        return true;
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
