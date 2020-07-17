package alpha.profile.controller;

import alpha.profile.model.Profile;

import alpha.profile.exceptions.UserNotFoundException;
import alpha.profile.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")

public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/user/{emailId}")
    public Optional<Profile> getUserByEmailId(@PathVariable("emailId") String emailId) {

        try {
            return profileService.getUserByEmailId(emailId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public Optional<Profile> getUserById(@PathVariable("userId") String userId) {
        try {
            return profileService.getUserById(userId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());

        }
    }

    @PostMapping("/user")
    public Profile createUser(@RequestBody Profile profile) {
        profile.setUserId(UUID.randomUUID().toString());
        return profileService.createUser(profile);

    }

    @DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable("userId") String userId) throws UserNotFoundException {
        profileService.deleteUserById(userId);
    }


    @PutMapping("/user/{userId}")
    public Profile updateUserById(@PathVariable("userId") String userId, @RequestBody String newName) {
        try {
            return profileService.updateUserById(userId, newName);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}