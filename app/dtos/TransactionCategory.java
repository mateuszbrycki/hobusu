package dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import models.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class TransactionCategory {

    public Long id;

    public String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    public LocalDateTime date;

    public User owner;

    public TransactionCategory() {
    }

    public TransactionCategory(String name) {
        this.name = name;
    }

    public TransactionCategory(Long id, String name, LocalDateTime date, User owner) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.owner = owner;
    }

}
