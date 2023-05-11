package com.ebcr.controller;

import com.ebcr.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping
   public String greeting(){
       return "Hello user well come to the authentication part";
   }
//   public User registerUser()
}
