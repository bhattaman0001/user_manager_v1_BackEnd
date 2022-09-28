package com.user_manager.userManagement.rest_controllers;

import com.user_manager.userManagement.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegisterApiController {
    @Autowired
    UserServices userServices;

    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("first_name") String first_name,
                                          @RequestParam("last_name") String last_name,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password){

        if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Please complete all Fields", HttpStatus.BAD_REQUEST);
        }

        // Encrypt / hash - password
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        // register new User
        int result = userServices.registerNewUserServiceMethod(first_name, last_name, email, hashed_password);

        if(result != 1){
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
