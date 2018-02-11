package ejb.session.stateless;

import entity.Address;
import util.Exception.AddressNotFoundException;

public interface AddressControllerLocal {

    public Address persistAddress(Address address);

    public void updateAddress(Address address);

    public Address retrieveAddressByAddressId(Long addressId) throws AddressNotFoundException;

    public void deleteAddress(Long addressId);

}
