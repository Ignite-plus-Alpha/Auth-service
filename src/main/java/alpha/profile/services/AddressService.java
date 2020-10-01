package alpha.profile.services;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.model.Address;
import alpha.profile.dao.AddressDao;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService {

    private final AddressDao addressDao;


    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public Address createAddress(Address address) {
        return addressDao.save(address);
    }


    public List<Address> getAllAddress(){
        return addressDao.findAll();
    }


    public List<Address> getAddressesByUserId(String userId) throws AddressNotFoundException {
        List<Address> address= addressDao.findByUserid(userId);

        if(address==null)
            throw new AddressNotFoundException("address not found");
        return address;
    }

    public Address updateAddressByUserIdAddressId(String userId,String addressId,Address newAddress) throws AddressNotFoundException {
        List<Address>  userAddresses = addressDao.findByUserid(userId);

        for (Address tempAddress:userAddresses
        ) {
            if(tempAddress.getAddressId().equals(addressId) )
            {   tempAddress.setAddressLine1(newAddress.getAddressLine1());
                tempAddress.setAddressLine2(newAddress.getAddressLine2());
                tempAddress.setCity(newAddress.getCity());
                tempAddress.setCountry(newAddress.getCountry());
                tempAddress.setState(newAddress.getState());
                tempAddress.setZipcode(newAddress.getZipcode());
                tempAddress.setContact(newAddress.getContact());
                tempAddress.setName(newAddress.getName());
                tempAddress.setAddressType(newAddress.getAddressType());
                 return addressDao.save(tempAddress);
            }
        }
        throw new AddressNotFoundException("address not found");

    }

    //get particular address by userId and AddressId
    public Address getAddressByUserIdAddressId(String userId,String addressId) {
        List<Address>  address = addressDao.findByUserid(userId);

        for (Address ad:address
        ) {
            if(ad.getAddressId().equals(addressId) )
            { return ad;
            }
        }
        return null;
    }

    //delete particular address by user and AddressId
    public void deleteAddressByUserIdAddressId(String userId,String addressId) {
        List<Address>  addresses = addressDao.findByUserid(userId);
        for (Address tempAddress:addresses
        ) {
            if(tempAddress.getAddressId().equals(addressId) )
            {  addressDao.delete(tempAddress);

            }
        }
    }

}