package com.pumpit.webservice.controller.dto;

import com.pumpit.webservice.model.entity.Authority;
import com.pumpit.webservice.model.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String profilePicturePath;
    private Set<Authority> authorities;
    private String company;
    private int clientCount;
}
