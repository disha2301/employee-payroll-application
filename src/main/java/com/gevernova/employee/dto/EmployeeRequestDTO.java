package com.gevernova.employee.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Z][a-z,A-Z]{2,}$", message = "Enter valid name")
    private String name;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Salary must be at least 1000")
    private Double salary;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters, include a digit, an uppercase letter, and a special character"
    )
    private String password;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "FEMALE|MALE|OTHERS", message = "Gender must be FEMALE, MALE, or OTHERS")
    private String gender;

    @Past(message = "DOB must be in the past")
    private LocalDate dob;

    @FutureOrPresent(message = "Join date cannot be in the past")
    private LocalDate joinDate;

    @NotEmpty(message = "Skills cannot be empty")
    private List<String> skills;
}
