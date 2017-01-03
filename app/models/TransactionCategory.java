package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
@Entity
@Table(name = "transaction_category")
public class TransactionCategory extends Model{

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    public String name;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    public LocalDateTime date;

    @NotNull
    @ManyToOne
    public User owner;

    public TransactionCategory() {
    }

    public TransactionCategory(String name) {
        this.name = name;
    }

    public TransactionCategory(String name, LocalDateTime date, User owner) {
        this.name = name;
        this.date = date;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "TransactionCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", owner=" + owner +
                '}';
    }
}
