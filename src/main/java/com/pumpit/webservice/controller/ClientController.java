package com.pumpit.webservice.controller;

import com.pumpit.webservice.model.entity.Client;
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
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
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
