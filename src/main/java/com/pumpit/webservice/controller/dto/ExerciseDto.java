package com.pumpit.webservice.controller.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExerciseDto {
    private long id;
    private String name;
}
