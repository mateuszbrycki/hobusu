package factories;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class UserFactory {

    public static dtos.User create(models.User user) {
        return new dtos.User(user.id, user.mail);
    }
}
