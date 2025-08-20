package me.sreejithnair.linkup.user_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be a valid email")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?=.*[0-9].*[0-9].*[0-9])(?=\\S*$).{8,}$",
            message = "Password must contain at least one uppercase letter, one special character, three numbers, and be minimum 8 characters long"
    )
    @Size(max = 64, message = "Password cannot exceed 64 characters")
    private String password;
}
