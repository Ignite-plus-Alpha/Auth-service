package alpha.profile.services;

import alpha.profile.dao.ProfileDao;
import alpha.profile.exceptions.UserNotFoundException;
import alpha.profile.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;

    //Createuser
    public Profile createUser(Profile profile) {
        return profileDao.save(profile);
    }

    public Optional<Profile> getUserByEmailId(String emailId) throws UserNotFoundException {
        Optional<Profile> user= profileDao.findById(emailId);

        if(!user.isPresent())
            throw new UserNotFoundException("user not found");
        return user;
    }

    public Optional<Profile> getUserById(String userId) throws UserNotFoundException {
        Optional<Profile> user= profileDao.findById(userId);

        if(!user.isPresent())
            throw new UserNotFoundException("user not found");
        return user;
    }



    public void deleteUserById(String userId) {

        Optional<Profile> user= profileDao.findById(userId);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in repo,enter correct details");
        }

        profileDao.deleteById(userId);

    }


    public Profile updateUserById(String userId, String newName) throws UserNotFoundException {

        Optional<Profile> userData = profileDao.findById(userId);

        if(profileDao.findById(userId).isPresent()) {
            Profile profile =userData.get();
            profile.setFirstName(newName);
            return profileDao.save(profile);
        }
        throw new UserNotFoundException("User not found");
    }

}
