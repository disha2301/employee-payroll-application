package com.gevernova.employee.service;

import com.gevernova.employee.dto.EmployeeRequestDTO;
import com.gevernova.employee.dto.EmployeeResponseDTO;
import com.gevernova.employee.entity.Employee;
import com.gevernova.employee.exceptionhandler.UserNotFoundException;
import com.gevernova.employee.mapper.EmployeeMapper;
import com.gevernova.employee.repository.EmployeeRepository;
import com.gevernova.employee.utlijms.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;
    private final EmailService emailService;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Employee employee = EmployeeMapper.mapToEntity(dto);
        Employee saved = repo.save(employee);
        log.debug("Saved employee entity: {}", saved);

        if (saved.getEmail() != null && !saved.getEmail().isEmpty()) {
            try {
                emailService.sendEmail(
                        saved.getEmail(),
                        "Welcome to Gevernova!",
                        "Hi " + saved.getName() + ", your employee account has been successfully created."
                );
                log.info("Welcome email sent to {}", saved.getEmail());
            } catch (MessagingException e) {
                log.error("Failed to send welcome email to {}", saved.getEmail(), e);
            }
        }

        return EmployeeMapper.mapToDTO(saved);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        return repo.findAll().stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDTO getEmployeeId(Long id) {
        Employee employee = repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with ID: {}", id);
                    return new UserNotFoundException("Employee not found with id: " + id);
                });
        return EmployeeMapper.mapToDTO(employee);
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Cannot update. Employee not found with ID: {}", id);
                    return new UserNotFoundException("Employee not found with id: " + id);
                });

        EmployeeMapper.updateEntity(existing, dto);
        Employee updated = repo.save(existing);
        log.debug("Updated employee entity: {}", updated);
        return EmployeeMapper.mapToDTO(updated);
    }

    public void deleteEmployee(Long id) {
        if (!repo.existsById(id)) {
            log.error("Cannot delete. Employee not found with ID: {}", id);
            throw new UserNotFoundException("Employee not found with id: " + id);
        }
        log.warn("Deleting employee with ID: {}", id);
        repo.deleteById(id);
    }
}
