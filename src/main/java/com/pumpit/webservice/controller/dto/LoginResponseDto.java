package com.pumpit.webservice.controller.dto;

import com.pumpit.webservice.model.entity.Authority;
import lombok.Data;

import java.util.Set;

@Data
public class LoginResponseDto {
    private long id;
    private Set<Authority> authorities;
    private boolean isSuccessful;
}
