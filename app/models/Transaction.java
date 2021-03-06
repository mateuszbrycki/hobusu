package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
@Entity
public class Transaction extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    public Double amount;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    public LocalDateTime date;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    public LocalDateTime creationDate;

    @NotNull
    public TransactionType type;

    @NotNull
    @ManyToOne
    public TransactionCategory category;

    @NotNull
    @ManyToOne
    public User owner;

    public String description;

    public LocalDateTime getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction() { }

    public Transaction(Double amount, LocalDateTime date, LocalDateTime creationDate, TransactionType type, TransactionCategory category, User owner, String description) {
        this.amount = amount;
        this.date = date;
        this.creationDate = creationDate;
        this.type = type;
        this.category = category;
        this.owner = owner;
        this.description = description;
    }
}
