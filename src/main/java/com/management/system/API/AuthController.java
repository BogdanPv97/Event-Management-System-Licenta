package com.management.system.API;

import com.management.system.Entity.DTO.Credentials;
import com.management.system.Entity.DTO.UserDetailsDTO;
import com.management.system.Service.AuthService;
import com.management.system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/loggedUser/{username}")
    public ResponseEntity<UserDetailsDTO> getLoggedUser(@PathVariable("username") String username){
        try{
            return new ResponseEntity<>(userService.getLoggedUser(username), HttpStatus.OK);
        }catch( Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
