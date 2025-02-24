package microservices1.employee_service.service;

import microservices1.employee_service.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private List<Employee> employeeListe = Arrays.asList(
            new Employee("Emp1", 1111, "developer"),
            new Employee("Emp2", 2222, "accountant"),
            new Employee("Emp3", 3333, "developer"),
            new Employee("Emp4", 4444, "salesperson"),
            new Employee("Emp5", 5555, "secretary"),
            new Employee("Emp6", 6666, "developer"));

    @Override
    public List<Employee> getAllEmployees() {
        return employeeListe;
    }
}
