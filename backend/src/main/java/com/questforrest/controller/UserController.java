package com.questforrest.controller;

import com.questforrest.dto.UserDto;
import com.questforrest.exception.InvalidTokenException;
import com.questforrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 09.10.16.
 */
@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUser(HttpRequest httpRequest) throws InvalidTokenException {
        String token = httpRequest.getHeaders() == null ? null :httpRequest.getHeaders().getFirst("token");
        return token == null ? new ResponseEntity(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(userService.getUserByToken(token), HttpStatus.OK);
    }
}
