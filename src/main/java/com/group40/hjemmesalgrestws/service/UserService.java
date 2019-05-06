package com.group40.hjemmesalgrestws.service;

import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, String id);

    UserDTO logInUser(UserLoginModel userLoginModel);
}
