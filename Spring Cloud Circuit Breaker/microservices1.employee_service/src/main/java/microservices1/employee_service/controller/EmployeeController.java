package microservices1.employee_service.controller;

import lombok.RequiredArgsConstructor;
import microservices1.employee_service.model.Employee;
import microservices1.employee_service.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }
}
