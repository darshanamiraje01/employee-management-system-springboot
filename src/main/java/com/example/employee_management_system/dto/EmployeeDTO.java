package com.example.employee_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Min(value = 1000, message = "Salary must be greater than 1000")
    private Double salary;

    @NotBlank(message = "Designation is required")
    private String designation;

    private Long departmentId;
}