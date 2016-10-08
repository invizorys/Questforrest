package com.questforrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 08.10.16.
 */
@RestController
public class NavigationController {
    @RequestMapping("/")
    public String helloPage(){
        return "Hello World";
    }
}
