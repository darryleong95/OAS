package ejb.session.stateless;

import entity.Address;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.Exception.AddressNotFoundException;

@Stateless
@Local(AddressControllerLocal.class)
@Remote(AddressControllerRemote.class)

public class AddressController implements AddressControllerRemote, AddressControllerLocal {

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;

    public AddressController() {
    }

    public Address persistAddress(Address address) {
        em.persist(address);
        em.flush();
        em.refresh(address);
        return address;
    }

    public void updateAddress(Address address) {
        em.merge(address);
    }

    public Address retrieveAddressByAddressId(Long addressId) throws AddressNotFoundException {
        Address address = em.find(Address.class, addressId);
        if (address == null) {
            throw new AddressNotFoundException("Address not found! \n");
        }
        return address;
    }

    public void deleteAddress(Long addressId) {
        try {
            Address addressToRemove = retrieveAddressByAddressId(addressId);
            em.remove(addressToRemove);
        } catch (AddressNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
