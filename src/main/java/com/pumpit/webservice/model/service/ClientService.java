package com.pumpit.webservice.model.service;

import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Training;

import java.util.List;

public interface ClientService {
    Client getClientById(Long id);

    List<Training> getTrainingsForClientId(Long id);

    void addNewClient(Client client);

    void updateClient(Client client);
}
