package com.notes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "Username Cannot Be Empty")
    private String username;
    @Email
    private String email;
    @Size(min = 6,message = "Minimum 6 Characters Required")
    private String password;
}
