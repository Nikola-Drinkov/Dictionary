package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.model.dto.LoginUserDTO;

public interface UserService {
    public void registerUser(RegisterUserDTO registerUserDTO);
    public void loginUser(LoginUserDTO loginUserDTO);
    public void logout();
}
