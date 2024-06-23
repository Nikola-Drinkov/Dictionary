package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private UserSession userSession;

    public HomeController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String isNotLogged() {
        if(userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String isLoggedIn(){
        if(!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "home";
    }
}
