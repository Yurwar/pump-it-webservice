package com.pumpit.webservice.model.service;

import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Trainer;

import java.util.Set;

public interface TrainerService {
    Trainer getTrainerById(Long id);

    Set<Client> getClientsForTrainerId(Long id);

    void addClientForTrainerId(Long id, Client client);

    void addNewTrainer(Trainer trainer);

    void updateTrainer(Trainer trainer);
}
