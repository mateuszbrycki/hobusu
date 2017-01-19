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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
