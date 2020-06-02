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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String profilePicturePath;
    private Set<Authority> authorities;
    private double weight;
    private int height;
    private String trainerFirstName;
    private String trainerLastName;
}
