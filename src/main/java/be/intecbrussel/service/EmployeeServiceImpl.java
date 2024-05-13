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

    @Override
    public void saveEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new IllegalStateException("email taken");
        }
         employeeRepository.save(employee);
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

