package com.jrp.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    public String displayEmployees(Model model) {
        Iterable<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {

        Employee anEmployee = new Employee();

        model.addAttribute("employee", anEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, @Valid Employee employee, Errors errors) {

        if(errors.hasErrors()){
            return "employees/employees-form";
        }

        // save to the database using an employee crud repository
        employeeService.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("id") long id, Model model) {

        Employee employee = employeeService.findByEmployeeId(id);

        model.addAttribute("employee", employee);


        return "employees/new-employee";
    }

    @GetMapping("delete")
    public String deleteEmployee(@RequestParam("id") long id, Model model) {
        Employee employee = employeeService.findByEmployeeId(id);
        employeeService.delete(employee);
        return "redirect:/employees";
    }

}