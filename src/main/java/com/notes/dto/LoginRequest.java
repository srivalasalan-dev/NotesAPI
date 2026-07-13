package com.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message = "Username Cannot Be Empty")
    private String username;
    @Size(min = 6, message = "Minimum 6 Characters Needed")
    private String password;
}
