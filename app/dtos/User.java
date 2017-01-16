package dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class User {

    public Long id;

    public String mail;

    public User() {
    }

    public User(Long id, String mail) {
        this.id = id;
        this.mail = mail;
    }
}
