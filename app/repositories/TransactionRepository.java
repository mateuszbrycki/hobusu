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

    public static List<Transaction> findLast(User user, Long limit) {
        return find
                .fetch("category")
                .where()
                .eq("owner.id", user.id)
                .orderBy("date desc")
                .setMaxRows(limit.intValue())
                .findList();
    }

    public static Transaction findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static List<Transaction> findFromCurrentMonth(User user) {
        return find
                .fetch("category")
                .where()
                .eq("owner.id", user.id)
                .eq("MONTH(date)", LocalDateTime.now().getMonth().getValue())
                .findList();
    }

    public static List<Transaction> findMostExpensiveTransactions(User user) {
        return find
                .fetch("category")
                .where()
                .eq("owner.id", user.id)
                .ge("date", LocalDateTime.now().minusMonths(3).toString())
                .orderBy("amount desc")
                .setMaxRows(10)
                .findList();
    }

    public static List<Transaction> findAllForDate(User user, Long year, Long month) {
        return find
                .fetch("category")
                .where()
                .eq("owner.id", user.id)
                .eq("YEAR(date)", year)
                .eq("MONTH(date)", month)
                .orderBy("date desc")
                .findList();
    }
}
