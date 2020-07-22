package alpha.profile.services;

import alpha.profile.dao.ProfileDao;
import alpha.profile.exceptions.UserNotFoundException;
import alpha.profile.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;



    //getAllGroups
    public List<Profile> getAll(){
        return profileDao.findAll();
    }

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

//    public Optional<Profile> getUserById(String userId) throws UserNotFoundException {
//        Optional<Profile> user= profileDao.findById(userId);
//
//        if(!user.isPresent())
//            throw new UserNotFoundException("user not found");
//        return user;
//    }



    public void deleteUserById(String emailId) {

        Optional<Profile> user= profileDao.findById(emailId);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in repo,enter correct details");
        }

        profileDao.deleteById(emailId);

    }


    public Profile updateUserById(String emailId, Profile profile) throws UserNotFoundException {

        Optional<Profile> userData = profileDao.findById(emailId);

        if(profileDao.findById(emailId).isPresent()) {
            Profile prev = userData.get();
            prev.setFirstName(profile.getFirstName());
            prev.setLastName(profile.getLastName());
            prev.setMobile(profile.getMobile());
            prev.setPassword(profile.getPassword());
            return profileDao.save(prev);
        }
        throw new UserNotFoundException("User not found");
    }

}
