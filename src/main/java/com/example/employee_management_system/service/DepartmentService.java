package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.DepartmentDTO;
import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.exception.ResourceNotFoundException;
import com.example.employee_management_system.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository;

    public Department createDepartment(DepartmentDTO dto) {

        Department department = new Department();
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());

        return repository.save(department);
    }

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department getDepartmentById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));
    }

    public void deleteDepartment(Long id) {

        Department department = getDepartmentById(id);
        repository.delete(department);
    }
}