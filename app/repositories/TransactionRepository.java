package repositories;

import com.avaje.ebean.Model;
import models.Transaction;
import models.User;

import java.util.List;

/**
 * Created by Mateusz Brycki on 03/01/2017.
 */
public class TransactionRepository {
    private static Model.Finder<String, Transaction> find = new Model.Finder<String, Transaction>(Transaction.class);

    public static List<Transaction> findAll(User user) {
        return find.where().eq("owner.id", user.id).findList();
    }

}
