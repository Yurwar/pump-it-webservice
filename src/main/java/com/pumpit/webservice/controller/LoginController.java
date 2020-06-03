package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.CredentialsDto;
import com.pumpit.webservice.controller.dto.LoginResponseDto;
import com.pumpit.webservice.controller.dto.UserDto;
import com.pumpit.webservice.model.entity.User;
import com.pumpit.webservice.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> loginUser(@RequestBody CredentialsDto credentialsDto) {
        User user = userService.getUserByUsername(credentialsDto.getUsername());
        boolean authenticated = userService.checkPasswordIdentity(user, credentialsDto.getPassword());

        LoginResponseDto responseDto = new LoginResponseDto();

        System.out.println("TRIGG");
        if (authenticated) {
            responseDto.setSuccessful(true);
            responseDto.setUser(buildUserDto(user));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    private UserDto buildUserDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .dateOfBirth(user.getDateOfBirth())
                .sex(user.getSex())
                .profilePicturePath(user.getProfilePicturePath())
                .authorities(user.getAuthorities())
                .build();
    }
}
