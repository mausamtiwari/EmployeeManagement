package be.intecbrussel.controller;

import be.intecbrussel.model.Employee;
import be.intecbrussel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    // Get list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> list = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", list);
        return "index";
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        //save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }


    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployee(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        //get employee from the services
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }


    @GetMapping(path = "/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }



}
