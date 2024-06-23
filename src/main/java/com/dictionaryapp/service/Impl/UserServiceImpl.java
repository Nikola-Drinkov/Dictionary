package com.dictionaryapp.service.Impl;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserSession userSession;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        if(userRepository.existsByUsername(registerUserDTO.getUsername())) throw new IllegalArgumentException("register.user.username.taken");
        if(userRepository.existsByEmail(registerUserDTO.getEmail())) throw new IllegalArgumentException("register.user.email.taken");
        if(!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) throw new IllegalArgumentException("register.user.username.passwords.not.match");

        User userToSave = modelMapper.map(registerUserDTO, User.class);
        userToSave.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        this.userRepository.save(userToSave);
    }

    @Override
    public void loginUser(LoginUserDTO loginUserDTO) {
        if(!userRepository.existsByUsername(loginUserDTO.getUsername())) throw new IllegalArgumentException("login.user.error");
        User userToLogin = this.userRepository.findByUsername(loginUserDTO.getUsername());
        if(!passwordEncoder.matches(loginUserDTO.getPassword(), userToLogin.getPassword())) throw new IllegalArgumentException("login.user.error");

        userSession.login(userToLogin);
    }

    @Override
    public void logout() {
        this.userSession.logout();
    }
}
