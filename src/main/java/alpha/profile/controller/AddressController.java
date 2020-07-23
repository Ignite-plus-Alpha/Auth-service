package alpha.profile.controller;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.exceptions.WalletNotFoundException;
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

    //get all addresses in db
    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    //getAll addresses Of a particular user by their emailId
    @GetMapping("/address/{emailId}")
    public List<Address> getAllAddressesByEmail(@PathVariable("emailId")String emailId) {
        try {
            return addressService.getAddressesByEmailId( emailId );
        } catch (AddressNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    //create an address
    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }


    //get a particular address of a user by their emailId/addressId
    @GetMapping("/address/{emailId}/{addressId}")
    public Address getAddressByEmailIdAddressId(@PathVariable("emailId") String emailId,@PathVariable("addressId") String addressId){
        return addressService.getAddressByEmailIdAddressId(emailId,addressId);
    }

    //update particular address of a user by their emailId and  corresponding AddressID
    @PutMapping("/address/{email}/{addressId}")
    public Address updateAddressByEmailAddressId(@PathVariable("email") String email,@PathVariable("addressId") String addressId,@RequestBody Address address) throws AddressNotFoundException{
        return addressService.updateAddressByEmailIdAddressId(email,addressId, address);
    }


    //delete a particular address of a user taking their emailId and the particular addressId
    @DeleteMapping("/address/{emailId}/{addressId}")
    public void deleteAddressByEmailIdWalletId(@PathVariable("emailId") String emailId,@PathVariable("addressId") String addressId) throws AddressNotFoundException {
        addressService.deleteAddressByEmailIdAddressId(emailId, addressId);
    }
}
