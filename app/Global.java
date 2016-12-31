
import com.avaje.ebean.Ebean;
import models.Transaction;
import models.TransactionCategory;
import models.TransactionType;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        // Check if the database is empty
        User user = new User("admin", "admin");

            /*Map dataFromFile = (Map) Yaml.load("initial-data.yml");
            Ebean.save((Map)dataFromFile);*/

            user.save();

            this.prepareTransactionCategory(user);
            this.prepareTransaction(user);

            user = new User("admin2", "admin2");
            user.save();

            this.prepareTransactionCategory(user);
            this.prepareTransaction(user);

    }

    private void prepareTransactionCategory(User user) {
        new TransactionCategory("Shopping", LocalDateTime.now(), user).save();
        new TransactionCategory("Insurance", LocalDateTime.now(), user).save();
        new TransactionCategory("Credit", LocalDateTime.now(), user).save();
        new TransactionCategory("Bills", LocalDateTime.now(), user).save();
    }

    private void prepareTransaction(User user) {
        new Transaction(30., LocalDateTime.now(), TransactionType.INCOME, TransactionCategory.findById(1L), user).save();
        new Transaction(-30., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategory.findById(2L), user).save();
        new Transaction(1000., LocalDateTime.now(), TransactionType.INCOME, TransactionCategory.findById(3L), user).save();
        new Transaction(-25., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategory.findById(4L), user).save();
    }
}
