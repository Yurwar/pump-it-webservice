package com.pumpit.webservice.controller;

import com.pumpit.webservice.model.entity.User;
import com.pumpit.webservice.model.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Model loginUser(@RequestBody String username,
                           @RequestBody String password,
                           Model model) {
        User user = userService.getUserByUsername(username);
        boolean authenticated = userService.checkPasswordIdentity(user, password);
        if (authenticated) {
            model.addAttribute("id", user.getId());
            model.addAttribute("authorities", user.getAuthorities());
            model.addAttribute("isSuccessful", true);
        } else {
            model.addAttribute("isSuccessful", false);
        }

        return model;
    }
}
