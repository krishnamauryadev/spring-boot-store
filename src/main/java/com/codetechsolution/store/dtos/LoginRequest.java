package com.codetechsolution.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email is required.")
    @Email
    private String email;
    @NotBlank(message = "password is required.")
    private String password;
}
