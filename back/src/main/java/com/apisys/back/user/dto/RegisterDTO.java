package com.apisys.back.user.dto;

import com.apisys.back.user.Role;

public record RegisterDTO(String firstname,String email, String password, String urlImage) {
}
