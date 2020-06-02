package com.pumpit.webservice.controller.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private boolean isSuccessful;
    private UserDto user;
}
