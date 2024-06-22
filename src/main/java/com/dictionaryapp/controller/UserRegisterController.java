package com.dictionaryapp.controller;

import com.dictionaryapp.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class UserRegisterController {
    private UserServiceImpl userService;

    public UserRegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }
}
