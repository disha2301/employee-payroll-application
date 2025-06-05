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

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Z][a-z,A-Z]{2,}$", message = "Enter valid name")
    private String name;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Salary must be atleast 1000")
    private Double salary;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$")
    private String password;

    @NotBlank
    @Pattern(regexp = "FEMALE|MALE|OTHERS")
    private String gender;

    @Past(message = "DOB must be in past")
    private LocalDate dob;

    @FutureOrPresent
    private LocalDate joinDate;

    @NotEmpty
    private List<String> skills;
}
