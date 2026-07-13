package com.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Username Cannot Be Empty")
         String username,
        @Size(min = 6, message = "Minimum 6 Characters Needed")
         String password
) {

}
