package com.dictionaryapp.controller;
import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserLoginController {
    private UserServiceImpl userService;
    private MessageSource messageSource;

    public UserLoginController(UserServiceImpl userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }
    @ModelAttribute("userLoginDTO")
    public LoginUserDTO setLoginUserDTO() {
        return new LoginUserDTO();
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("userLoginDTO") @Valid LoginUserDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            return "redirect:/login";
        }
        try{
            userService.loginUser(userLoginDTO);
        }catch (IllegalArgumentException e){
            String errorMessage = messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
            System.out.println(errorMessage);

            redirectAttributes.addFlashAttribute("serverError", errorMessage);
            return "redirect:/login";
        }
        return "redirect:/home";
    }
    @PostMapping("/logout")
    public String logoutUser() {
        this.userService.logout();
        return "redirect:/";
    }
}
