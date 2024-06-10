package com.skypro.hw6.service;

import com.skypro.hw6.exception.EmployeeAlreadyAddedException;
import com.skypro.hw6.exception.EmployeeNotFoundException;
import com.skypro.hw6.exception.EmployeeStorageIsFullException;
import com.skypro.hw6.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceMapImpl implements EmployeeService {
    private static final int NUMBER_OF_EMPLOYEES = 5;
    private final Map<String, Employee> employees = new HashMap<>(NUMBER_OF_EMPLOYEES);

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= NUMBER_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Массив переполнен");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("Этот сотрудник уже был добавлен");
        }
        employees.put(firstName + lastName, employee);
        return employee;
    }

    @Override
    public Employee delete(String firstName, String lastName) {
        Employee employee = employees.remove(firstName + lastName);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException(firstName + " " + lastName + " не найден");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(firstName + lastName);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException(firstName + " " + lastName + " не найден");
    }
}
