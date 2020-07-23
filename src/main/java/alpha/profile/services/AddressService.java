package alpha.profile.services;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.model.Address;
import alpha.profile.dao.AddressDao;
import alpha.profile.model.Profile;
import alpha.profile.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;


    public Address createAddress(Address address) {
        return addressDao.save(address);
    }


    public List<Address> getAllAddress(){
        return addressDao.findAll();
    }


    public List<Address> getAddressesByEmailId(String email) throws AddressNotFoundException {
        List<Address> address= addressDao.findByEmail(email);

        if(address==null)
            throw new AddressNotFoundException("address not found");
        return address;
    }

    public Address updateAddressByEmailIdAddressId(String email,String addressId,Address newAddress) throws AddressNotFoundException {
        List<Address>  userAddresses = addressDao.findByEmail(email);

        for (Address tempAddress:userAddresses
        ) {
            if(tempAddress.getAddressId().equals(addressId) )
            {   tempAddress.setAddressLine1(newAddress.getAddressLine1());
                tempAddress.setAddressLine2(newAddress.getAddressLine2());
                tempAddress.setCity(newAddress.getCity());
                tempAddress.setCountry(newAddress.getCountry());
                tempAddress.setState(newAddress.getState());
                tempAddress.setZipcode(newAddress.getZipcode());
                 return addressDao.save(tempAddress);
            }
        }
        throw new AddressNotFoundException("address not found");

    }

    //get particular address by email and AddressId
    public Address getAddressByEmailIdAddressId(String email,String addressId) {
        List<Address>  address = addressDao.findByEmail(email);

        for (Address ad:address
        ) {
            if(ad.getAddressId().equals(addressId) )
            { return ad;
            }
        }
        return null;
    }

    //delete particular address by email and AddressId
    public void deleteAddressByEmailIdAddressId(String email,String addressId) {
        List<Address>  addresses = addressDao.findByEmail(email);
        for (Address tempAddress:addresses
        ) {
            if(tempAddress.getAddressId().equals(addressId) )
            {  addressDao.delete(tempAddress);
            }
        }

    }




//    public Address updateAddressById(String addressId,String addressline1) throws AddressNotFoundException {
//        Optional<Address> addressData = addressDao.findById(addressId);
//
//        if(addressDao.findById(addressId).isPresent()) {
//            Address address=addressData.get();
//            address.setAddressLine1(addressline1);
//            return addressDao.save(address);
//        }
//      throw new AddressNotFoundException("Address not found");
//    }
}