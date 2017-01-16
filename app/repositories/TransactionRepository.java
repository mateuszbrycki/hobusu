package repositories;

import com.avaje.ebean.Model;
import models.Transaction;
import models.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mateusz Brycki on 03/01/2017.
 */
public class TransactionRepository {
    private static Model.Finder<String, Transaction> find = new Model.Finder<String, Transaction>(Transaction.class);

    public static List<Transaction> findAll(User user) {
        return find.where().eq("owner.id", user.id).findList();
    }

    public static List<Transaction> findLast10(User user) {
        return find
                .fetch("category")
                .where()
                .eq("owner.id", user.id)
                .orderBy("date desc")
                .setMaxRows(10)
                .findList();
    }

    public static List<Transaction> findFromCurrentMonth(User user) {
        return find.where()
                .eq("owner.id", user.id)
                .eq("MONTH(date)", LocalDateTime.now().getMonth().getValue())
                .findList();
    }
}
