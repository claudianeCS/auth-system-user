package com.apisys.back.user.dto;

import com.apisys.back.user.Role;
import lombok.Data;

@Data
public class RegistroDTO {

    private String username;
    private String password;
    private Role role; // padrao de registro de usuario e USER
}
