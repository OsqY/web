package com.oscar.web.services;

import com.oscar.web.dto.RegistrationDto;
import com.oscar.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
