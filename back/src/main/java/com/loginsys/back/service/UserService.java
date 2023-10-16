package com.loginsys.back.service;

import com.loginsys.back.entity.LoginMessage;
import com.loginsys.back.entity.User;
import com.loginsys.back.entity.dto.LoginDTO;
import com.loginsys.back.entity.dto.UserDTO;

import java.util.List;


public interface UserService {

    List<User> getAllUser();

    String addUser(UserDTO userDTO);

    LoginMessage loginUser(LoginDTO loginDTO);
}
