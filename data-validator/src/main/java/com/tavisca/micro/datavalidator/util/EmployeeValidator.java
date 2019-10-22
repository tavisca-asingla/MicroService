package com.tavisca.micro.datavalidator.util;

import com.tavisca.micro.datavalidator.model.Employee;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class EmployeeValidator {
    public boolean isValid(Employee employee){
        usernameIsValid(employee.getUsername());
        isDesignationValid(employee.getDesignation());
        isPasswordValid(employee.getPassword());
        isSalaryValid(employee.getSalary());

        return true;
    }

    private boolean usernameIsValid(String username){
        if(username==null || username.length()<=3)
            throw new IllegalArgumentException("Username should be atleast 3 chars in length");
        return true;
    }

    private boolean isPasswordValid(String password){
        if(password == null || password.length()<=6)
            throw new IllegalArgumentException("Password should be min 6 chars.");
        return  true;
    }

    private boolean isSalaryValid(int salary){
        if(salary>1000000 || salary<12)
            throw new IllegalArgumentException("Not possible to have such salary in my company");
        return  true;
    }

    private boolean isDesignationValid(String designation){
        if(designation==null)
            throw new IllegalArgumentException("Please give a designation to urself");
        return  true;
    }
}
