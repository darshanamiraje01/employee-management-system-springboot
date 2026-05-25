package com.example.employee_management_system.controller;

import com.example.employee_management_system.dto.DepartmentDTO;
import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;

    @PostMapping
    public Department createDepartment(
            @Valid @RequestBody DepartmentDTO dto) {

        return service.createDepartment(dto);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return service.getDepartmentById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {

        service.deleteDepartment(id);

        return "Department deleted successfully";
    }
}