package com.dictionaryapp.controller;

import com.dictionaryapp.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class UserLogoutController {
    private UserServiceImpl userService;

    public UserLogoutController(UserServiceImpl userService) {
        this.userService = userService;
    }
}
