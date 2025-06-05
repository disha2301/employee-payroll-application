package com.gevernova.employee.controller;

import com.gevernova.employee.dto.EmployeeRequestDTO;
import com.gevernova.employee.dto.EmployeeResponseDTO;
import com.gevernova.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

     // POST: http://localhost:8080/employees

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
        EmployeeResponseDTO savedEmployee = service.createEmployee(dto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // GET: http://localhost:8080/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeId(id));
    }

    // PUT: http://localhost:8080/employees/{id}

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id,
                                                              @Valid @RequestBody EmployeeRequestDTO dto) {
        EmployeeResponseDTO updated = service.updateEmployee(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE: http://localhost:8080/employees/{id}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted with ID: " + id);
    }
}
