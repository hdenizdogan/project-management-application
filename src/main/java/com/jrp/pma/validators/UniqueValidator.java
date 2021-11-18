package com.jrp.pma.validators;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueValidator implements ConstraintValidator<UniqueValue,String> {


    @Autowired
    EmployeeService employeeService;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        System.out.println("Entered validation method...");
        Optional<Employee> employee = employeeService.findByEmail(value);
        if(employee.isPresent())
            return false;
        else
            return true;
    }
}
