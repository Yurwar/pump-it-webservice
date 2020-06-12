package com.pumpit.webservice.model.service.impl;

import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Trainer;
import com.pumpit.webservice.model.entity.User;
import com.pumpit.webservice.model.repository.ClientRepository;
import com.pumpit.webservice.model.repository.TrainerRepository;
import com.pumpit.webservice.model.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultUserService implements UserService {
    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;

    public DefaultUserService(ClientRepository clientRepository, TrainerRepository trainerRepository) {
        this.clientRepository = clientRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    @Override
    public boolean checkPasswordIdentity(User user, String actualPassword) {
        if (user.getPassword() == null) {
            return false;
        } else {
            return user.getPassword().equals(actualPassword);
        }
    }

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> clientByUsername = clientRepository.findClientByUsername(username);
        Optional<Trainer> trainerByUsername = trainerRepository.findTrainerByUsername(username);

        if (clientByUsername.isPresent()) {
            return clientByUsername.get();
        } else if (trainerByUsername.isPresent()) {
            return trainerByUsername.get();
        } else {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }
}
