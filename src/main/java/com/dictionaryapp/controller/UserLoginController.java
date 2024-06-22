package com.dictionaryapp.controller;
import com.dictionaryapp.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class UserLoginController {
    private UserServiceImpl userService;

    public UserLoginController(UserServiceImpl userService) {
        this.userService = userService;
    }
}
