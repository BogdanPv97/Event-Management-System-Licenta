package com.management.system.API;

import com.management.system.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    public void registerUser(){}

//    @PostMapping("/login")
//    public void login(){}
}
