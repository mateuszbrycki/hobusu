package models;

import com.avaje.ebean.Model;

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
    public LocalDateTime date;

    @NotNull
    public TransactionType type;

    @NotNull
    @ManyToOne
    public TransactionCategory category;

    private static Model.Finder<String, Transaction> find = new Model.Finder<String, Transaction>(Transaction.class);

    public Transaction() { }

    public Transaction(Double amount, LocalDateTime date, TransactionType type, TransactionCategory category) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
    }

    public static List<Transaction> findAll() {
        return Transaction.find.all();
    }
}
