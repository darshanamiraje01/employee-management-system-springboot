package com.example.employee_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;
}