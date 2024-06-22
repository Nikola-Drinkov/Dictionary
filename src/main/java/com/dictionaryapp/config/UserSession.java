package com.dictionaryapp.config;

import com.dictionaryapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {
    private Long id;
    private String username;

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
    public boolean isLoggedIn() {
        return this.id != null;
    }
    public void logout(){
        this.id = null;
        this.username = null;
    }
    public String username(){
        return username;
    }
    public Long userId() {
        return id;
    }
}
