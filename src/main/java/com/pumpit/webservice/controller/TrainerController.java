package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.TrainerDto;
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
    public TrainerDto getTrainerById(@PathVariable Long id) {
        Trainer trainer = trainerService.getTrainerById(id);

        return TrainerDto.builder()
                .username(trainer.getUsername())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .authorities(trainer.getAuthorities())
                .clientCount(trainer.getClients().size())
                .company(trainer.getCompany())
                .dateOfBirth(trainer.getDateOfBirth())
                .id(trainer.getId())
                .profilePicturePath(trainer.getProfilePicturePath())
                .sex(trainer.getSex())
                .build();
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
