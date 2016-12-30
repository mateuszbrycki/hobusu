
import com.avaje.ebean.Ebean;
import models.TransactionCategory;
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
        if(TransactionCategory.findAll().size() == 0) {

//            Map dataFromFile = (Map) Yaml.load("initial-data.yml");
//            Ebean.save((Map)dataFromFile);

            new TransactionCategory("Shopping", LocalDateTime.now()).save();
            new TransactionCategory("Insurance", LocalDateTime.now()).save();
            new TransactionCategory("Credit", LocalDateTime.now()).save();
            new TransactionCategory("Bills", LocalDateTime.now()).save();
        }
    }
}
