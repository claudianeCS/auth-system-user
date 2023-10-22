package com.apisys.back.user.dto;

import com.apisys.back.user.Role;

public record RegisterDTO(String email, String password, Role role) {
}
