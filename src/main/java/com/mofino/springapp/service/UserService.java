package com.mofino.springapp.service;

import com.mofino.springapp.domain.entities.models.service.UserServiceModel;

public interface UserService {

    boolean registerUser(UserServiceModel userServiceModel);

    UserServiceModel loginUser(UserServiceModel userServiceModel);

}
