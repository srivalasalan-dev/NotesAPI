package com.notes.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequest(
        @NotBlank
         String refreshToken
) {


}
