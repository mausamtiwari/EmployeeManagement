package be.intecbrussel.service;

import be.intecbrussel.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(Long employeeId);

    void deleteEmployeeById(Long employeeId);

}




