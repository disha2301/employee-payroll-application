package com.gevernova.employee.mapper;

import com.gevernova.employee.dto.EmployeeRequestDTO;
import com.gevernova.employee.dto.EmployeeResponseDTO;
import com.gevernova.employee.entity.Employee;

public class EmployeeMapper {

    // Convert DTO to Entity
    public static Employee mapToEntity(EmployeeRequestDTO dto) {
        if (dto == null) return null;

        return Employee.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .department(dto.getDepartment())
                .salary(dto.getSalary())
                .password(dto.getPassword())
                .gender(dto.getGender())
                .dob(dto.getDob())
                .joinDate(dto.getJoinDate())
                .skills(dto.getSkills())
                .build();
    }

    // Convert Entity to Response DTO
    public static EmployeeResponseDTO mapToDTO(Employee employee) {
        if (employee == null) return null;

        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .name(employee.getName())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .gender(employee.getGender())
                .dob(employee.getDob())
                .joinDate(employee.getJoinDate())
                .skills(employee.getSkills())
                .build();
    }

    // For updating an existing employee
    public static void updateEntity(Employee employee, EmployeeRequestDTO dto) {
        employee.setEmail(dto.getEmail());
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setPassword(dto.getPassword());
        employee.setGender(dto.getGender());
        employee.setDob(dto.getDob());
        employee.setJoinDate(dto.getJoinDate());
        employee.setSkills(dto.getSkills());
    }
}
