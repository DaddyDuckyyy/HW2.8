package com.skypro.hw6.service;

import com.skypro.hw6.model.Employee;

public interface EmployeeService {
    Employee add(String firstName, String lastName);
    Employee delete(String firstName, String lastName);
    Employee find(String firstName, String lastName);

}
