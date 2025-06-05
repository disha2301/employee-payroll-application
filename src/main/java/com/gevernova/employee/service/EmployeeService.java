package com.gevernova.employee.service;

import com.gevernova.employee.dto.EmployeeRequestDTO;
import com.gevernova.employee.dto.EmployeeResponseDTO;
import com.gevernova.employee.entity.Employee;
import com.gevernova.employee.exceptionhandler.UserNotFoundException;
import com.gevernova.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }

    // Map request DTO to entity
    private Employee mapToEntity(EmployeeRequestDTO dto){
        return Employee.builder()
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

    // Map entity to response DTO
    private EmployeeResponseDTO mapToDTO(Employee employee){
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .gender(employee.getGender())
                .dob(employee.getDob())
                .joinDate(employee.getJoinDate())
                .skills(employee.getSkills())
                // omit password for security reasons
                .build();
    }

    // Save new employee to DB
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto){
        Employee employee = mapToEntity(dto);
        Employee saved = repo.save(employee);
        log.debug("Saved employee entity: {}", saved);
        return mapToDTO(saved);
    }

    // Get all employees
    public List<EmployeeResponseDTO> getAllEmployees(){
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public EmployeeResponseDTO getEmployeeId(Long id){
        Employee employee = repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with ID: {}", id);
                    return new UserNotFoundException("Employee not found with id: " + id);
                });
        return mapToDTO(employee);
    }

    // Update existing employee
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto){
        Employee existing = repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Cannot update. Employee not found with ID: {}", id);
                    return new RuntimeException("Employee not found with id: " + id);
                });

        existing.setName(dto.getName());
        existing.setDepartment(dto.getDepartment());
        existing.setSalary(dto.getSalary());
        existing.setPassword(dto.getPassword());
        existing.setGender(dto.getGender());
        existing.setDob(dto.getDob());
        existing.setJoinDate(dto.getJoinDate());
        existing.setSkills(dto.getSkills());

        Employee saved = repo.save(existing);
        log.debug("Updated employee entity: {}", saved);
        return mapToDTO(saved);
    }

    // Delete employee
    public void deleteEmployee(Long id){
        log.warn("Deleting employee with ID: {}", id);
        repo.deleteById(id);
    }
}
