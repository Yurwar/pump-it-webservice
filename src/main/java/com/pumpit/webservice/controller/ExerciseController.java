package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.ExerciseDto;
import com.pumpit.webservice.model.service.ExerciseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<ExerciseDto> getExercises() {
        return exerciseService.getAllExercises().stream().map(exercise -> ExerciseDto.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .build()).collect(Collectors.toList());
    }
}
