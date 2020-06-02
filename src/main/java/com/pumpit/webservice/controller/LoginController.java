package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.CredentialsDto;
import com.pumpit.webservice.controller.dto.LoginResponseDto;
import com.pumpit.webservice.model.entity.User;
import com.pumpit.webservice.model.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody CredentialsDto credentialsDto) {
        User user = userService.getUserByUsername(credentialsDto.getUsername());
        boolean authenticated = userService.checkPasswordIdentity(user, credentialsDto.getPassword());

        LoginResponseDto responseDto = new LoginResponseDto();

        if (authenticated) {
            responseDto.setId(user.getId());
            responseDto.setAuthorities(user.getAuthorities());
            responseDto.setSuccessful(true);
        } else {
            responseDto.setSuccessful(false);
        }

        return responseDto;
    }
}
