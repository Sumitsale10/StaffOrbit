package com.stafforbit.stafforbit.service.impl;
import com.stafforbit.stafforbit.entity.Employee;
import com.stafforbit.stafforbit.exception.ResourceNotFoundException;
import com.stafforbit.stafforbit.repository.EmployeeRepository;
import com.stafforbit.stafforbit.service.EmployeeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
	private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getEmail() != null && repo.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        return repo.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        repo.delete(existing);
    }

    @Override
    public List<Employee> searchByName(String keyword) {
        return repo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword);
    }
}
