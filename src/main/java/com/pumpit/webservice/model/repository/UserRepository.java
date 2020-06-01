package com.pumpit.webservice.model.repository;

import com.pumpit.webservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
}
