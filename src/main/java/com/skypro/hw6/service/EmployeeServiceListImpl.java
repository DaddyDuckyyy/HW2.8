package com.skypro.hw6.service;

import com.skypro.hw6.exception.EmployeeAlreadyAddedException;
import com.skypro.hw6.exception.EmployeeNotFoundException;
import com.skypro.hw6.exception.EmployeeStorageIsFullException;
import com.skypro.hw6.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceListImpl implements EmployeeService {
    private static final int NUMBER_OF_EMPLOYEES = 5;
    private List<Employee> employees = new ArrayList<>();

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= NUMBER_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Массив переполнен");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Этот сотрудник уже был добавлен");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee delete(String firstName, String lastName) {
        Employee employee1 = new Employee(firstName, lastName);
        for (Employee employee2 : employees) {
            if (employee2.equals(employee1)) {
                employees.remove(employee2);
                return employee2;
            }
        }
        throw new EmployeeNotFoundException(firstName + " " + lastName + " не найден");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee1 = new Employee(firstName, lastName);
        for (Employee employee2 : employees) {
            if (employee2.equals(employee1)) {
                return employee2;
            }
        }
        throw new EmployeeNotFoundException(firstName + " " + lastName + " не найден");
    }
}
