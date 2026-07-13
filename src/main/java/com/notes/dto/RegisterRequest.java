package com.notes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Username Cannot Be Empty")
         String username,
        @Email
         String email,
        @Size(min = 6,message = "Minimum 6 Characters Required")
         String password
) {

}
