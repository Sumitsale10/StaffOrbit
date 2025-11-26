package com.stafforbit.stafforbit.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stafforbit.stafforbit.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	 List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
	 boolean existsByEmail(String email);
}
