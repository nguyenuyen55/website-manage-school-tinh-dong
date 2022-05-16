package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.dto.request.LoginRequest;
import com.example.websitemanageschooltinhdong.dto.response.LoginResponse;
import com.example.websitemanageschooltinhdong.security.jwt.JwtTokenProvider;
import com.example.websitemanageschooltinhdong.security.service.CustomUserDetails;
import com.example.websitemanageschooltinhdong.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @GetMapping("home")
    public String home(){
        return "Welcome to web tinh dong";
    }
    @GetMapping("user")
    public String home1(){
        return "Welcome to user";
    }
    @GetMapping("admin")
    public String home5(){
        return "Welcome to admin";
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginResponse(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        String jwt = null;
        String status = "";
        HttpStatus httpStatus = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            status = "Success";
            httpStatus = HttpStatus.OK;
        } catch (DisabledException disabledException) {
            status = "Account locked";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (BadCredentialsException badCredentialsException) {
            status = "Wrong password";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            status = "Username not exists";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception exception) {
            status = "Error server";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new LoginResponse(jwt,status),httpStatus);

    }
}
