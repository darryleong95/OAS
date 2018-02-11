package ejb.session.stateless;

import entity.CreditTransaction;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.Exception.InvalidCreditTransactionException;

@Stateless
@Local(CreditTransactionControllerLocal.class)
@Remote(CreditTransactionControllerRemote.class)
public class CreditTransactionController implements CreditTransactionControllerRemote, CreditTransactionControllerLocal {

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;

    @Override
    public CreditTransaction createNewCreditTransaction(CreditTransaction creditTransaction) {
        em.persist(creditTransaction);
        em.flush();
        return creditTransaction;
    }

    @Override
    public List<CreditTransaction> retrieveAllCreditTransactions() {
        Query query = em.createQuery("SELECT s FROM CreditTransaction s");
        return query.getResultList();
    }

    @Override
    public CreditTransaction retrieveByCreditTransactionId(Long creditTransactionId) throws InvalidCreditTransactionException {
        CreditTransaction creditTransaction = em.find(CreditTransaction.class, creditTransactionId);
        if(creditTransaction != null){
            return creditTransaction;
        } 
        else{
            throw new InvalidCreditTransactionException("Invalid creditTransaction ID");
        }
    }

    @Override
    public void updateCreditTransaction(CreditTransaction creditTransaction){
        em.merge(creditTransaction);
    }

    public void deleteCreditTransaction(Long creditTransactionId) throws InvalidCreditTransactionException {
        CreditTransaction creditTransaction = em.find(CreditTransaction.class, creditTransactionId);
        try {
            em.remove(creditTransaction);

        } catch (NoResultException ex) {
            throw new InvalidCreditTransactionException("Invalid bid ID. CreditTransaction does not exists.");
        }
    }
}
