package ejb.session.stateless;

import entity.CreditTransaction;
import java.util.List;
import util.Exception.InvalidCreditTransactionException;

public interface CreditTransactionControllerLocal {

    public CreditTransaction createNewCreditTransaction(CreditTransaction creditTransaction);

    public List<CreditTransaction> retrieveAllCreditTransactions();

    public CreditTransaction retrieveByCreditTransactionId(Long creditTransactionId) throws InvalidCreditTransactionException;

    public void updateCreditTransaction(CreditTransaction creditTransaction);

    public void deleteCreditTransaction(Long creditTransactionId) throws InvalidCreditTransactionException;

}
