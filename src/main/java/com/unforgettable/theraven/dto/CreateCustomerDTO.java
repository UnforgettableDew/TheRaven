package com.unforgettable.theraven.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Size(min = 2, max = 100, message = "Email must be between 2 and 100 characters")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+\\d{6,14}$", message = "Phone must start with '+' and contain only digits, between 6 and 14 characters")
    private String phone;
}
