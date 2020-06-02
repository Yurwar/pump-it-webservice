package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.ClientDto;
import com.pumpit.webservice.controller.dto.TrainerDto;
import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Trainer;
import com.pumpit.webservice.model.entity.Training;
import com.pumpit.webservice.model.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);

        return ClientDto.builder()
                .username(client.getUsername())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .authorities(client.getAuthorities())
                .dateOfBirth(client.getDateOfBirth())
                .id(client.getId())
                .profilePicturePath(client.getProfilePicturePath())
                .sex(client.getSex())
                .height(client.getHeight())
                .weight(client.getWeight())
                .trainerFirstName(client.getTrainer().getFirstName())
                .trainerLastName(client.getTrainer().getLastName())
                .build();
    }

    @GetMapping("/{id}/trainings")
    public List<Training> getTrainingsForClientId(@PathVariable Long id) {
        return clientService.getTrainingsForClientId(id);
    }

    @PostMapping
    public void addNewClient(@RequestBody Client client) {
        clientService.addNewClient(client);
    }
}
