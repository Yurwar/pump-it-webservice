package com.pumpit.webservice.model.service.impl;

import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Trainer;
import com.pumpit.webservice.model.repository.TrainerRepository;
import com.pumpit.webservice.model.service.TrainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
public class DefaultTrainerService implements TrainerService {
    private final TrainerRepository trainerRepository;

    public DefaultTrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.getOne(id);
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public void addClientForTrainerId(Long trainerId, Client newClient) {
        Trainer trainer = getTrainerById(trainerId);
        addClientForTrainer(trainer, newClient);
        trainerRepository.save(trainer);
    }

    @Override
    public Set<Client> getClientsForTrainerId(Long id) {
        return trainerRepository.getOne(id).getClients();
    }

    @Override
    public void addNewTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    private void addClientForTrainer(Trainer trainer, Client newClient) {
        Set<Client> clients = trainer.getClients();
        clients.add(newClient);
        trainer.setClients(clients);
    }
}
