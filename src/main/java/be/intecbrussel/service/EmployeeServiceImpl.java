package be.intecbrussel.service;

import be.intecbrussel.model.Employee;
import be.intecbrussel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // This ensure n
    @Override
    public void saveEmployee(Employee employee) {
        if (employee.getId() != null) {
            // Editing an existing employee
            Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee.getId());
            if (existingEmployeeOptional.isPresent()) {
                Employee existingEmployee = existingEmployeeOptional.get();
                if (!existingEmployee.getEmail().equals(employee.getEmail())) {
                    // Check if the new email is taken by another employee
                    Optional<Employee> emailCheckEmployee = employeeRepository.findEmployeeByEmail(employee.getEmail());
                    if (emailCheckEmployee.isPresent()) {
                        throw new IllegalStateException("email taken");
                    }
                }
                // Save the updated employee details
                employeeRepository.save(employee);
            } else {
                throw new IllegalStateException("employee not found");
            }
        } else {
            // Creating a new employee
            Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(employee.getEmail());
            if (optionalEmployee.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            employeeRepository.save(employee);
        }
    }


    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee= employeeRepository.findById(id);
        Employee employee=null;
        if(optionalEmployee.isPresent()){
            employee=optionalEmployee.get();
        }else {
            throw new RuntimeException("Employee not found id"+id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);

    }

}

