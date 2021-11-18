package com.jrp.pma.api.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees(){

        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id")Long id){
        return employeeService.findById(id).get();
    }

    @PostMapping//(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PatchMapping(path="/{id}",consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id")Long id, @RequestBody @Valid Employee patchEmployee){
        Employee employee = employeeService.findById(id).get();

        if(patchEmployee.getEmail() != null){
            employee.setEmail(patchEmployee.getEmail());
        }
        if(patchEmployee.getFirstName() != null){
            employee.setFirstName(patchEmployee.getFirstName());
        }
        if(patchEmployee.getLastName() != null){
            employee.setLastName(patchEmployee.getLastName());
        }
        if(patchEmployee.getProjects() != null){
                employee.setProjects(patchEmployee.getProjects());
        }

        return employeeService.save(employee);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id")Long id){
        try{
            employeeService.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){

        }
    }

    @GetMapping(params={"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page")int page,
                                                     @RequestParam("size")int size){
        Pageable pageAndSize = PageRequest.of(page,size);
        return employeeService.findAll(pageAndSize);

    }
}
