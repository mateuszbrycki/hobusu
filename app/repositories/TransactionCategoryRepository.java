package repositories;

import com.avaje.ebean.Model;
import models.TransactionCategory;
import models.User;

import java.util.List;

/**
 * Created by Mateusz Brycki on 03/01/2017.
 */
public class TransactionCategoryRepository {

    private static Model.Finder<String, TransactionCategory> find
            = new Model.Finder<String, TransactionCategory>(TransactionCategory.class);

    public static TransactionCategory findById(Long id) {
        return find.ref(id.toString());
    }

    public static List<TransactionCategory> findAll(User user) {

        return find.where().eq("owner.id", user.id).findList();
    }
}
