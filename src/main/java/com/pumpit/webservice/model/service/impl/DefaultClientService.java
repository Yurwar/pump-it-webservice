package com.pumpit.webservice.model.service.impl;

import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Training;
import com.pumpit.webservice.model.repository.ClientRepository;
import com.pumpit.webservice.model.service.ClientService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultClientService implements ClientService, UserDetailsService {
    private final ClientRepository clientRepository;

    public DefaultClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findClientByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " not found"));
    }

    @Override
    public void addNewClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Training> getTrainingsForClientId(Long id) {
        return getClientById(id).getTrainings();
    }
}
