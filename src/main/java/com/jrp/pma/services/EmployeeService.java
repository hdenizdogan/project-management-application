package com.jrp.pma.services;

import com.jrp.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
    public Iterable<Employee> findAll(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }
    public List<EmployeeProject> employeeProjects(){
        return employeeRepository.employeeProjects();
    }

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public Employee findByEmployeeId(Long id){
        return employeeRepository.findByEmployeeId(id);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
