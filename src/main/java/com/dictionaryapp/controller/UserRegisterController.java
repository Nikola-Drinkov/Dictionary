package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
public class UserRegisterController {
    private UserServiceImpl userService;
    private MessageSource messageSource;

    public UserRegisterController(UserServiceImpl userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }
    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO setUpRegisterUserDTO() {
        return new RegisterUserDTO();
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerUserDTO") @Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            registerUserDTO.setConfirmPassword("");
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO", bindingResult);
            return "redirect:/register";
        }
        else{
            try{
                userService.registerUser(registerUserDTO);
            }catch (IllegalArgumentException e){
                String errorMessage = messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
                System.out.println(errorMessage);

                redirectAttributes.addFlashAttribute("serverError", errorMessage);
                return "redirect:/register";
            }
        }
        return "index";
    }
}
