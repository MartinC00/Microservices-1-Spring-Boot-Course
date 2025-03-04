package microservices1.employee_service.service;

import microservices1.employee_service.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
}
