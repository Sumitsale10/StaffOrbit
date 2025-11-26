package com.stafforbit.stafforbit.controller;

import com.stafforbit.stafforbit.entity.Employee;
import com.stafforbit.stafforbit.service.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController 
{
	 private final EmployeeService service;

	    public EmployeeController(EmployeeService service) {
	        this.service = service;
	    }

	    @PostMapping
	    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
	        Employee saved = service.createEmployee(employee);
	        return ResponseEntity.status(201).body(saved);
	    }

	    @GetMapping
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	        return ResponseEntity.ok(service.getAllEmployees());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
	        return ResponseEntity.ok(service.getEmployeeById(id));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
	        return ResponseEntity.ok(service.updateEmployee(id, employee));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	        service.deleteEmployee(id);
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/search")
	    public ResponseEntity<List<Employee>> search(@RequestParam("q") String q) {
	        return ResponseEntity.ok(service.searchByName(q));
	    }
}
