package factories;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class TransactionFactory {

    public static dtos.Transaction create(models.Transaction transaction) {
        return new dtos.Transaction(
                transaction.id,
                transaction.amount,
                transaction.date,
                transaction.creationDate,
                transaction.type,
                TransactionCategoryFactory.create(transaction.category),
                UserFactory.create(transaction.owner),
                transaction.description
        );
    }
}
