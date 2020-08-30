package com.example.CRUD_Application_springboot.controller;

import com.example.CRUD_Application_springboot.model.User;
import com.example.CRUD_Application_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm) {
        userService.save(userForm);

        return "redirect:/";
    }

    @GetMapping("/user")
    public String userProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);

        return "user";
    }
}
