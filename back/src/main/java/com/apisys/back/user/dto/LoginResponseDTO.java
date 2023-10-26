package com.apisys.back.user.dto;

import com.apisys.back.user.Role;

public record LoginResponseDTO(String token, Role role) {
}
