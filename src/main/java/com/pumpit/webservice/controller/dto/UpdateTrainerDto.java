package com.pumpit.webservice.controller.dto;

import com.pumpit.webservice.model.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTrainerDto {
    private String firstName;
    private String lastName;
    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
    private Sex sex;
    private String company;
}
