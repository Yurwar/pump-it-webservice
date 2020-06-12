package com.pumpit.webservice.model.repository;

import com.pumpit.webservice.model.entity.Trainer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends UserRepository<Trainer> {
    Optional<Trainer> findTrainerByUsername(String username);
}
