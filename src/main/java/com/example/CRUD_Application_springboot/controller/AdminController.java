package com.example.CRUD_Application_springboot.controller;

import com.example.CRUD_Application_springboot.model.User;
import com.example.CRUD_Application_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("users", userService.users());

        return "admin";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("userForm", new User());

        return "user-create";
    }

    @PostMapping("/user-create")
    public String saveUser(@ModelAttribute("userForm") User userForm) {
        userService.save(userForm);

        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id , Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.update(user);

        return "redirect:/admin";
    }
}
