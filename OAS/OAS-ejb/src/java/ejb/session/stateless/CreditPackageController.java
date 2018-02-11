package ejb.session.stateless;

import entity.CreditPackage;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.Exception.InvalidCreditPackageException;

@Stateless
@Local(CreditPackageControllerLocal.class)
@Remote(CreditPackageControllerRemote.class)
public class CreditPackageController implements CreditPackageControllerRemote, CreditPackageControllerLocal {

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;

    @Override
    public CreditPackage createNewCreditPackage(CreditPackage creditPackage) {
        em.persist(creditPackage);
        em.flush();
        em.refresh(creditPackage);
        return creditPackage;
    }

    @Override
    public List<CreditPackage> retrieveAllCreditPackages() {
        Query query = em.createQuery("SELECT s FROM CreditPackage s");
        return query.getResultList();
    }

    @Override
    public CreditPackage retrieveByCreditPackageId (Long creditPackageId) throws InvalidCreditPackageException {
        CreditPackage creditPackage = em.find(CreditPackage.class, creditPackageId);
        if (creditPackage != null) {
            return creditPackage;
        } else {
            throw new InvalidCreditPackageException("Invalid creditPackage ID");
        }
    }

    @Override
    public CreditPackage retrieveCreditPackageByAmtCredit(double amtCredit) throws InvalidCreditPackageException {
        Query query = em.createQuery("SELECT cp FROM CreditPackage cp WHERE cp.amtCredit = :inAmtCredit");
        query.setParameter("inAmtCredit", amtCredit);
        try {
            return (CreditPackage) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new InvalidCreditPackageException ("Credit Package of " + amtCredit + " does not exist!");
        }
    }

    @Override
    public void updateCreditPackage(CreditPackage creditPackage) {
        em.merge(creditPackage);
    }

    @Override
    public void deleteCreditPackage(Long creditPackageId) throws InvalidCreditPackageException {
        CreditPackage creditPackage = em.find(CreditPackage.class, creditPackageId);
        try {
            em.remove(creditPackage);

        } catch (NoResultException ex) {
            throw new InvalidCreditPackageException("Invalid creditPackage ID. CreditPackage does not exists.");
        }
    }
}
