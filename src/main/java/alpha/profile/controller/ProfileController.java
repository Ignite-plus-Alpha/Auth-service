package alpha.profile.controller;

import alpha.profile.exceptions.WalletNotFoundException;
import alpha.profile.model.Profile;

import alpha.profile.exceptions.UserNotFoundException;
import alpha.profile.services.ProfileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello world";
    }


    @ApiOperation(value = "get list of all users in db")
    @GetMapping("/user")
    public List<Profile> getAllUsers() {
        return profileService.getAll();
    }

    @ApiOperation(value = "check if user is already registered")
    @GetMapping("/register/{emailId}")
    public String registerUser(@PathVariable("emailId") String emailId) throws UserNotFoundException {
        return profileService.createUserIfNotExist(emailId);
    }

    @ApiOperation(value = "get a user's detail by their email id")
    @GetMapping("/user/{emailId}")
    public Optional<Profile> getUserByEmailId(@PathVariable("emailId") String emailId) {

        try {
            return profileService.getUserByEmailId(emailId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

//    @GetMapping("/email/{emailId}")
//    public Optional<Profile> getUserById(@PathVariable("emailId") String emailId) {
//        try {
//            return profileService.getUserById(emailId);
//        } catch (UserNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
//
//        }
//    }
    @ApiOperation(value = "create a user")
    @PostMapping("/user")
    public Profile createUser(@RequestBody Profile profile) {
        profile.setUserId(UUID.randomUUID().toString());
        return profileService.createUser(profile);

    }
    @ApiOperation(value = "delete a user by their email id")
    @DeleteMapping("/user/{emailId}")
    public void deleteUserById(@PathVariable("emailId") String emailId) throws UserNotFoundException {
        profileService.deleteUserById(emailId);
    }

    @ApiOperation(value = "update a user by their email id")
    @PutMapping("/user/{emailId}")
    public Profile updateUserById(@PathVariable("emailId") String emailId, @RequestBody Profile profile) {
        try {
            return profileService.updateUserById(emailId, profile);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    //update default address
    @ApiOperation(value = "update default address of a user by their email id")
    @PutMapping("/user/address/{emailId}")
    public Profile updateDefaultAddressByUserId(@PathVariable("emailId") String emailId, @RequestBody String addressId ) {
        try {
            return profileService.updateDefaultAddress(emailId, addressId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    //update default wallet
    @ApiOperation(value = "update exp of default wallet of a user by their email id")
    @PutMapping("/user/wallet/{emailId}")
    public Profile updateDefaultWalletByUserId(@PathVariable("emailId") String emailId, @RequestBody String walletId ) {
        try {
            return profileService.updateDefaultWallet(emailId, walletId);
        } catch (WalletNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}