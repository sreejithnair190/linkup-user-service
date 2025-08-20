package me.sreejithnair.linkup.user_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 100, message = "Number of characters in name should be in range (3-100)")
    @Pattern(
            regexp = "^[a-zA-Z ]+$",
            message = "Name can only contain letters and spaces"
    )
    private String name;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?=.*[0-9].*[0-9].*[0-9])(?=\\S*$).{8,}$",
            message = "Password must contain at least one uppercase letter, one special character, three numbers, and be minimum 8 characters long"
    )
    @Size(max = 64, message = "Password cannot exceed 64 characters")
    private String password;
}
