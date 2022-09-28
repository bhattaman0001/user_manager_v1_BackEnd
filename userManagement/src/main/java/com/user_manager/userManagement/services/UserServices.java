package com.user_manager.userManagement.services;

import com.user_manager.userManagement.models.User;
import com.user_manager.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String fname, String lname, String email, String password){
        return userRepository.registerNewUser(fname, lname, email, password);
    }
    // end of register new user service method

    public List<String> checkUserEmail(String email){
        return userRepository.checkUserEmail(email);
    }
    // end of check user email services method:

    public String checkUserPasswordByEmail(String email){
        return userRepository.checkUserPasswordByEmail(email);
    }
    // end of check user password by email services method:

    public User getUserDetailsByEmail(String email){
        return userRepository.getUserDetailsByEmail(email);
    }
    // end of check user details by email services method:
}
