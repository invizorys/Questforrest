package com.questforrest.controller;

import com.questforrest.dto.CredentialDto;
import com.questforrest.dto.RegistrationRequestDto;
import com.questforrest.dto.UserDto;
import com.questforrest.exception.InvalidTokenException;
import com.questforrest.exception.UserAlreadyExistException;
import com.questforrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by root on 08.10.16.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    public static final String TOKEN_HEADER = "Authorization";
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationRequestDto requestDto) throws UserAlreadyExistException {
        UserDto userDto = userService.register(requestDto);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody CredentialDto credential) {
        UserDto userDto = userService.login(credential.getLogin(), credential.getPassword());
        if(userDto != null){
            return new ResponseEntity(userDto, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/vk/authorize", method = RequestMethod.POST)
    public ResponseEntity vkAuthorize(@RequestBody UserDto userDto, HttpRequest httpRequest) throws InvalidTokenException {
        String token = httpRequest.getHeaders().getFirst(TOKEN_HEADER);
        UserDto authorizedUser = userService.vkAuthorize(userDto, token);
        if(authorizedUser != null){
            return new ResponseEntity(userDto, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "User with such login already exist.")
    public void userAlreadyExist(){}

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid Token")
    public void invalidUserToken(){}
}

