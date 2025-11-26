package com.stafforbit.stafforbit.service;

import java.util.List;

import com.stafforbit.stafforbit.entity.Employee;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    void deleteEmployee(Long id);
    List<Employee> searchByName(String keyword);
}
