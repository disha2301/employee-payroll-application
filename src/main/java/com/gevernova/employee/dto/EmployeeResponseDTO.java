package com.gevernova.employee.dto;

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
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String department;
    private Double salary;
    private String gender;
    private LocalDate dob;
    private LocalDate joinDate;
    private List<String> skills;
}
