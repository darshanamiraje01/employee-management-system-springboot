package com.example.employee_management_system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Double salary;

    private String designation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;
}