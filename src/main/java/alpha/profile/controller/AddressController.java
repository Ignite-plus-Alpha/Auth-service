package alpha.profile.controller;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.model.Address;
import alpha.profile.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/address/{emailId}")
    public List<Address> getAllAddressesByEmail(@PathVariable("emailId")String emailId) {
        try {
            return addressService.getAddressesByEmailId( emailId );
        } catch (AddressNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }


    //get address by emailId/addressId
    @GetMapping("/address/{emailId}/{addressId}")
    public Address getAddressByEmailIdAddressId(@PathVariable("emailId") String emailId,@PathVariable("addressId") String addressId){
        return addressService.getAddressByEmailIdAddressId(emailId,addressId);
    }

//    @DeleteMapping("/address/{addressId}")
//    public void deleteAddressById(@PathVariable("addressId") String addressId) throws AddressNotFoundException {
//        addressService.deleteAddressById(addressId);
//    }
//
    @PutMapping("/address/{email}/{addressId}")
    public Address updateAddressByEmailAddressId(@PathVariable("email") String email,@PathVariable("addressId") String addressId,@RequestBody Address address) throws AddressNotFoundException{
        return addressService.updateAddressByEmailIdAddressId(email,addressId, address);
    }
}
