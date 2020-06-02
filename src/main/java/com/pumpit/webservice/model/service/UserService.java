package com.pumpit.webservice.model.service;

import com.pumpit.webservice.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    boolean checkPasswordIdentity(User user, String actualPassword);
}
