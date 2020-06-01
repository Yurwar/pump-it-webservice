package com.pumpit.webservice.model.repository;

import com.pumpit.webservice.model.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends UserRepository<Client> {
}
