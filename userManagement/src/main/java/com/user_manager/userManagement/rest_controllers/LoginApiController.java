package com.user_manager.userManagement.rest_controllers;

import com.user_manager.userManagement.models.Login;
import com.user_manager.userManagement.models.User;
import com.user_manager.userManagement.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LoginApiController {

    @Autowired
    UserServices userService;

    @PostMapping("/user/login")
    public ResponseEntity authenticateUser(@RequestBody Login login){
        // get user email
        List<String> userEmail = userService.checkUserEmail(login.getEmail());

        // check if email is empty
        if(userEmail.isEmpty() || userEmail == null){
            return new ResponseEntity("Email does not exist", HttpStatus.NOT_FOUND);
        }
        // end of check if email is empty

        // get hashed user password
        String hashed_password = userService.checkUserPasswordByEmail(login.getEmail());

        // validate the password
        if(!BCrypt.checkpw(login.getPassword(), hashed_password)){
            return new ResponseEntity("Incorrect Username or Password", HttpStatus.BAD_REQUEST);
        }

        // set user object
        User user = userService.getUserDetailsByEmail(login.getEmail());
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
