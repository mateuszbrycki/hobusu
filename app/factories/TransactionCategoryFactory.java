package factories;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class TransactionCategoryFactory {

    public static dtos.TransactionCategory create(models.TransactionCategory category) {
        return new dtos.TransactionCategory(category.id, category.name, category.date, UserFactory.create(category.owner));
    }
}
