package com.pumpit.webservice.controller;

import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Trainer;
import com.pumpit.webservice.model.service.TrainerService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/{id}")
    public Trainer getTrainerById(@PathVariable Long id) {
        return trainerService.getTrainerById(id);
    }

    @GetMapping("/{id}/clients")
    public Set<Client> getClientsForTrainer(@PathVariable Long id) {
        return trainerService.getClientsForTrainerId(id);
    }

    @PostMapping("/{id}/clients")
    public void addClientForTrainer(@PathVariable Long id,
                                    @RequestBody Client client) {
        trainerService.addClientForTrainerId(id, client);
    }

    @PostMapping
    public void addNewTrainer(@RequestBody Trainer trainer) {
        trainerService.addNewTrainer(trainer);
    }
}
