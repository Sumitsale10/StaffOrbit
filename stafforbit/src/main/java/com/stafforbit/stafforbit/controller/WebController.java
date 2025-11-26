package com.stafforbit.stafforbit.controller;
import com.stafforbit.stafforbit.entity.Employee;
import com.stafforbit.stafforbit.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")   // base path for views
public class WebController 
{

    private final EmployeeService employeeService;

    public WebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Show list page (server-side rendered)
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list";
    }

    // Show create form
    @GetMapping("/employees/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    // Server-side processing of create form (optional)
    @PostMapping("/employees")
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/staff/employees";
    }

    // Show edit form
    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee e = employeeService.getEmployeeById(id);
        model.addAttribute("employee", e);
        return "employees/edit";
    }

    // Server-side processing of edit form (optional)
    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        employeeService.updateEmployee(id, employee);
        return "redirect:/staff/employees";
    }

    // Show detail page (optional)
    @GetMapping("/employees/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/details";
    }
}
