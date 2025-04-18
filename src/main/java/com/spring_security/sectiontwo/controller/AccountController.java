package com.spring_security.sectiontwo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/myAccount")
    public String account() {
        return "Here are the account details from the DB";
    }
}
