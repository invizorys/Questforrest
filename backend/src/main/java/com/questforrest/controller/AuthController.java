package com.questforrest.controller;

import com.questforrest.dto.CredentialDto;
import com.questforrest.dto.RegistrationRequestDto;
import com.questforrest.dto.UserDto;
import com.questforrest.exception.InvalidTokenException;
import com.questforrest.exception.UserAlreadyExistException;
import com.questforrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by root on 08.10.16.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationRequestDto requestDto) throws UserAlreadyExistException {
        UserDto userDto = userService.register(requestDto);
        return getResponseEntity(userDto);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody CredentialDto credential) {
        UserDto userDto = userService.login(credential.getLogin(), credential.getPassword());
        return getResponseEntity(userDto);
    }

    @CrossOrigin
    @RequestMapping(value = "/vk/authorize", method = RequestMethod.POST)
    public ResponseEntity vkAuthorize(@RequestBody RegistrationRequestDto regDto) throws InvalidTokenException {
        UserDto authorizedUser = userService.vkAuthorize(regDto.getAccessToken(), regDto.getUserId());
        return authorizedUser == null ?new ResponseEntity(HttpStatus.UNAUTHORIZED) :new ResponseEntity<>(authorizedUser, HttpStatus.ACCEPTED);
    }

    private ResponseEntity getResponseEntity(UserDto authorizedUser) {
        return authorizedUser == null ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED) : new ResponseEntity<>(authorizedUser, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "User with such login already exist.")
    public void userAlreadyExist() {
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid Token")
    public void invalidUserToken() {
    }
}

