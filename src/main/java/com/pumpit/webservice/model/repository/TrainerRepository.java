package com.pumpit.webservice.model.repository;

import com.pumpit.webservice.model.entity.Trainer;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends UserRepository<Trainer> {
}
