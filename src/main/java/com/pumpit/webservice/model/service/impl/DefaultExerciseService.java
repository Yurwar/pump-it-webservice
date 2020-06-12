package com.pumpit.webservice.model.service.impl;

import com.pumpit.webservice.model.entity.Exercise;
import com.pumpit.webservice.model.repository.ExerciseRepository;
import com.pumpit.webservice.model.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultExerciseService implements ExerciseService {
    private ExerciseRepository exerciseRepository;

    public DefaultExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
}
