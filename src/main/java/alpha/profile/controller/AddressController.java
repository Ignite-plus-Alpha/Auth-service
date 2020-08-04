package alpha.profile.controller;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.exceptions.WalletNotFoundException;
import alpha.profile.model.Address;
import alpha.profile.services.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    //get all addresses in db
    @ApiOperation(value = "this will give all addresses in our db")
    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    //getAll addresses Of a particular user by their userId
    @ApiOperation(value = "this will give all addresses of a user by their user id")
    @GetMapping("/address/{userId}")
    public List<Address> getAllAddressesByUserId(@PathVariable("userId")String userId) {
        try {
            return addressService.getAddressesByUserId( userId );
        } catch (AddressNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    //create an address
    @ApiOperation(value = "lets us create an address entry for a user")
    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }


    //get a particular address of a user by their userId/addressId
    @ApiOperation(value = "lets us get a particular address info of a user by providing user id and address id")
    @GetMapping("/address/{userId}/{addressId}")
    public Address getAddressByUserIdAddressId(@PathVariable("userId") String userId,@PathVariable("addressId") String addressId){
        return addressService.getAddressByUserIdAddressId(userId,addressId);
    }

    //update particular address of a user by their userId and  corresponding AddressID
    @ApiOperation(value = "allows update of a particular address info of a user by providing user id and address id")
    @PutMapping("/address/{userId}/{addressId}")
    public Address updateAddressByUserIdAddressId(@PathVariable("userId") String userId,@PathVariable("addressId") String addressId,@RequestBody Address address) throws AddressNotFoundException{
        return addressService.updateAddressByUserIdAddressId(userId,addressId, address);
    }


    //delete a particular address of a user taking their userId and the particular addressId
    @ApiOperation(value = "lets us delete a particular address of a user by providing user id and address id")
    @DeleteMapping("/address/{userId}/{addressId}")
    public void deleteAddressByUserIdWalletId(@PathVariable("userId") String userId,@PathVariable("addressId") String addressId) throws AddressNotFoundException {
        addressService.deleteAddressByUserIdAddressId(userId, addressId);
    }
}
