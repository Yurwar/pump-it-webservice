package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.ClientDto;
import com.pumpit.webservice.controller.dto.RegisterRequestDTO;
import com.pumpit.webservice.model.entity.Authority;
import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Trainer;
import com.pumpit.webservice.model.entity.Training;
import com.pumpit.webservice.model.service.ClientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/clients")
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

                .trainerFirstName(Optional.ofNullable(client.getTrainer())
                .map(Trainer::getFirstName).orElse(null))

                .trainerLastName(Optional.ofNullable(client.getTrainer())
                        .map(Trainer::getLastName).orElse(null))
                .build();
    }

    @GetMapping("/{id}/trainings")
    public List<Training> getTrainingsForClientId(@PathVariable Long id) {
        return clientService.getTrainingsForClientId(id);
    }

    @PostMapping(name="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewClient(@RequestBody RegisterRequestDTO registerRequestDTO) {
        Client client = new Client();
        client.setUsername(registerRequestDTO.getUsername());
        client.setFirstName(registerRequestDTO.getFirstName());
        client.setLastName(registerRequestDTO.getLastName());
        client.setPassword(registerRequestDTO.getPassword());
        client.setDateOfBirth(registerRequestDTO.getDateOfBirth());
        client.setEnabled(Boolean.TRUE);
        client.setAuthorities(Set.of(Authority.CLIENT));
        clientService.addNewClient(client);
    }
}
