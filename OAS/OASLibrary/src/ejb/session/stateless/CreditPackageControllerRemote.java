package ejb.session.stateless;

import entity.CreditPackage;
import java.util.List;
import javax.ejb.Remote;
import util.Exception.InvalidCreditPackageException;

public interface CreditPackageControllerRemote {

    public CreditPackage createNewCreditPackage(CreditPackage creditPackage);

    public List<CreditPackage> retrieveAllCreditPackages();

    public CreditPackage retrieveByCreditPackageId(Long creditPackageId) throws InvalidCreditPackageException;

    public CreditPackage retrieveCreditPackageByAmtCredit(double amtCredit) throws InvalidCreditPackageException;

    public void updateCreditPackage(CreditPackage creditPackage);

    public void deleteCreditPackage(Long creditPackageId) throws InvalidCreditPackageException;
    
}
