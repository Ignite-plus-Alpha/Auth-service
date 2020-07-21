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

    @GetMapping("/address/{addressId}")
    public Optional<Address> getAddressById(@PathVariable("addressId")String addressId) {
        try {
            return addressService.getAddressById( addressId );
        } catch (AddressNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @DeleteMapping("/address/{addressId}")
    public void deleteAddressById(@PathVariable("addressId") String addressId) throws AddressNotFoundException {
        addressService.deleteAddressById(addressId);
    }

    @PutMapping("/address/{addressId}")
    public Address updateAddressByIdById(@PathVariable("addressId") String addressId,@RequestBody String address) throws AddressNotFoundException{
        return addressService.updateAddressById(addressId, address);
    }
}
