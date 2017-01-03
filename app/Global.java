
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.Transaction;
import models.TransactionCategory;
import models.TransactionType;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Json;
import play.libs.Yaml;
import repositories.TransactionCategoryRepository;
import repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        this.registerMappers();
        this.setupDatabase();

    }

    private void registerMappers() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JSR310Module());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Json.setObjectMapper(mapper);
    }

    private void setupDatabase() {

        if(UserRepository.findById("1") == null) {
            // Check if the database is empty
            User user = new User("admin", "admin");
            user.save();

            this.prepareTransactionCategory(user);
            this.prepareTransaction(user);

            user = new User("admin2", "admin2");
            user.save();

            this.prepareTransactionCategory(user);
            this.prepareTransaction(user);
        }
    }

    private void prepareTransactionCategory(User user) {
        new TransactionCategory("Shopping", LocalDateTime.now(), user).save();
        new TransactionCategory("Insurance", LocalDateTime.now(), user).save();
        new TransactionCategory("Credit", LocalDateTime.now(), user).save();
        new TransactionCategory("Bills", LocalDateTime.now(), user).save();
    }

    private void prepareTransaction(User user) {
        new Transaction(30., LocalDateTime.now(), TransactionType.INCOME, TransactionCategoryRepository.findById(1L), user).save();
        new Transaction(-30., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategoryRepository.findById(2L), user).save();
        new Transaction(1000., LocalDateTime.now(), TransactionType.INCOME, TransactionCategoryRepository.findById(3L), user).save();
        new Transaction(-25., LocalDateTime.now(), TransactionType.OUTCOME, TransactionCategoryRepository.findById(4L), user).save();
    }
}
