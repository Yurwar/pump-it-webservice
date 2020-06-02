package com.pumpit.webservice.controller.dto;

import com.pumpit.webservice.model.entity.Authority;
import com.pumpit.webservice.model.entity.Sex;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String profilePicturePath;
    private Set<Authority> authorities;
}