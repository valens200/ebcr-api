package com.ebcr.controller;

import com.ebcr.dtos.LoginRequestDTO;
import com.ebcr.models.AppUser;
import com.ebcr.models.RegisterRequestDTO;
import com.ebcr.models.Role;
import com.ebcr.services.IUserService;
import com.ebcr.utils.AlgorithmGenerator;
import com.ebcr.utils.Mapper;
import com.ebcr.utils.TokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    Map<String, String> messages = new HashMap<>();
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;
    AlgorithmGenerator algorithmGenerator;
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    IUserService userService;
    @GetMapping
   public String greeting(){
       return "Hello user well come to the authentication part";
   }
    @PostMapping("/register")
    public AppUser registerUser(@RequestBody RegisterRequestDTO registerRequestDTO, HttpServletResponse response, HttpServletRequest request) throws IOException {
        log.info("user {}", registerRequestDTO);
        AppUser user = Mapper.getUserFromDTO(registerRequestDTO);
        String inputs[] = new String[]{user.getPassword(), user.getEmail(), user.getUserName()};
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] == "" || inputs[i] == null) {
                messages.put("status", "failed");
                messages.put("error_message", "Please fill out all the fields are required");
                response.setStatus(400);
                new ObjectMapper().writeValue(response.getOutputStream(), messages);
                break;
            }
        }
        AppUser isUserAvailable = userService.findByEmail(user.getEmail());
        if (isUserAvailable != null) {
            messages.clear();
            response.setStatus(400);
            messages.put("error_message", "Account with that email already registered please try another");
            new ObjectMapper().writeValue(response.getOutputStream(), messages);
            return null;
        }
        messages.clear();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser registeredUser = userService.registerUser(user);

        Collection<Role> roles = new ArrayList<>();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
        });

        com.example.demo.utils.SignUpTokenGenerator signUpTokenGenerator = new com.example.demo.utils.SignUpTokenGenerator(registeredUser, roles, authorities, algorithmGenerator);
        String access_token = signUpTokenGenerator.getAccessTOken(response, request);
        String refresh_token = signUpTokenGenerator.getRefreshToken(request, response);
        messages.put("acccess_Token", access_token);
        messages.put("refresh_Token", refresh_token);
        new ObjectMapper().writeValue(response.getOutputStream(), messages);
        return registeredUser;
    }


    @PostMapping("/login")

    public void loginUser(@RequestBody LoginRequestDTO loginRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String inputs[] = new String[]{loginRequest.getEmail(), loginRequest.getPassword()};
            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i] == "" || inputs[i] == null) {
                    messages.put("error_message", "Invalid inputs please fill out all the fields");
                    new ObjectMapper().writeValue(response.getOutputStream(), messages);
                    break;
                }

            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            TokenGenerator tokenGenerator1 = new TokenGenerator(user, algorithmGenerator);
            String access_token = tokenGenerator1.getAccessToken(response, request);
            String refresh_token = tokenGenerator1.getfreshToken(request, response);

            messages.put("access_token", access_token);
            messages.put("refresh_token", refresh_token);
            messages.put("message", "Your logged in successfully");
            new ObjectMapper().writeValue(response.getOutputStream(), messages);
        } catch (Exception exception) {
            log.error("eror {}", exception.getMessage());
        }

    }

}
