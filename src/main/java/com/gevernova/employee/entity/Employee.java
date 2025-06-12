package com.gevernova.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String department;
    private Double salary;
    private String password;
    private String gender;
    private LocalDate dob;
    private LocalDate joinDate;

    @ElementCollection // to persist List<String> in JPA
    private List<String> skills;

}
