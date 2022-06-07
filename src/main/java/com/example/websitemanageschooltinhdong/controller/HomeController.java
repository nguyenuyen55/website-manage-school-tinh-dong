package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.dto.request.LoginRequest;
import com.example.websitemanageschooltinhdong.dto.request.NguoiDungDTO;
import com.example.websitemanageschooltinhdong.dto.response.LoginResponse;
import com.example.websitemanageschooltinhdong.exception.ErrorResponse;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.NguoiDungRepository;
import com.example.websitemanageschooltinhdong.security.jwt.JwtTokenProvider;
import com.example.websitemanageschooltinhdong.security.service.CustomUserDetails;
import com.example.websitemanageschooltinhdong.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
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
        String username="";
        GrantedAuthority role=null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            username= ((CustomUserDetails) authentication.getPrincipal()).getUsername();
            role=((CustomUserDetails) authentication.getPrincipal()).getAuthorities().iterator().next();
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
        return new ResponseEntity<>(new LoginResponse(jwt,status,username,role.getAuthority()),httpStatus);

    }
    @GetMapping("/{username}")
    public ResponseEntity<NguoiDungDTO> nguoiDungResponseEntity(@PathVariable("username") String username){
        return new ResponseEntity<>(nguoiDungRepository.findByTenDangNhapDTO(username),HttpStatus.OK);
    }
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", false, details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
