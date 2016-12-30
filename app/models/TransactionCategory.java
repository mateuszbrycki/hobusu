package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    public LocalDateTime date;

    private static Model.Finder<String, TransactionCategory> find
            = new Model.Finder<String, TransactionCategory>(TransactionCategory.class);

    public TransactionCategory(String name) {
        this.name = name;
    }

    public TransactionCategory(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
    }

    public static TransactionCategory findById(Long id) {
        return find.ref(id.toString());
    }

    public static List<TransactionCategory> findAll() {
        return find.all();
    }
}
