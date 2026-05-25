package com.example.employee_management_system.controller;

import com.example.employee_management_system.dto.EmployeeDTO;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public Employee createEmployee(
            @Valid @RequestBody EmployeeDTO dto) {

        return service.createEmployee(dto);
    }

    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        return service.getAllEmployees(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return service.getEmployeeById(id);
    }

    @GetMapping("/filter")
    public List<Employee> filterEmployees(
            @RequestParam Double minSalary) {

        return service.filterEmployeesBySalary(minSalary);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        service.deleteEmployee(id);

        return "Employee deleted successfully";
    }
}