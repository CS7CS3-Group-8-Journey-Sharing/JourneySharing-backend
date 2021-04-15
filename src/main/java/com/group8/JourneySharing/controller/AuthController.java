package com.group8.JourneySharing.controller;

import com.group8.JourneySharing.entity.jwt.JwtRequest;
import com.group8.JourneySharing.entity.jwt.JwtResponse;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.service.AuthService;
import com.group8.JourneySharing.utility.JwtUtility;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<JwtResponse> signUp( @RequestBody @Valid NewUserVo newUser) throws Exception {
        LOGGER.info("Sign Up initiated: " + newUser.toString() );
        String email = userService.addUser(newUser);
        LOGGER.info("Sign Up completed: " + newUser.toString() );
        JwtRequest tokenRequest = new JwtRequest(email, newUser.getPassword());
        return logIn(tokenRequest);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> logIn(@RequestBody JwtRequest jwtRequest) throws Exception {
        LOGGER.info("Login initiated: " + jwtRequest.getEmail() );
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(), jwtRequest.getPassword()
                    )
            );
        } catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        final String token = jwtUtility.generateToken(userDetails);
        UserDetailsVo user = userService.getUserByEmail(jwtRequest.getEmail());
        LOGGER.info("Login completed: " + jwtRequest.getEmail() );
        return new ResponseEntity<>(new JwtResponse(token, user), HttpStatus.ACCEPTED);
    }
}
