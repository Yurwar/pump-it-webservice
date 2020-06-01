package com.pumpit.webservice.model.repository;

import com.pumpit.webservice.model.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends UserRepository<Client> {
    Optional<Client> findClientByUsername(String username);
}
