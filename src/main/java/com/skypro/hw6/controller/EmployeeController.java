package com.skypro.hw6.controller;

import com.skypro.hw6.model.Employee;
import com.skypro.hw6.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @GetMapping("/add")
    public String addEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return generateMessage(service.add(firstName,lastName), "ДОБАВЛЕН");
    }
    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return generateMessage(service.delete(firstName,lastName),"УДАЛЁН");
    }
    @GetMapping("/find")
    public String findEmployee(@RequestParam  String firstName, @RequestParam String lastName){
        return generateMessage(service.find(firstName,lastName), "НАЙДЕН");
    }

    private String generateMessage(Employee employee,String result){
        return employee.toString()+result ;
    }
}
