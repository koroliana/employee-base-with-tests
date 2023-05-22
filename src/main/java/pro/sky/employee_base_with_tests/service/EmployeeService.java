package pro.sky.employee_base_with_tests.service;

import org.springframework.stereotype.Service;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.EmployeeAlreadyAddedException;
import pro.sky.employee_base_with_tests.exception.EmployeeNotFoundException;
import pro.sky.employee_base_with_tests.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    private static final int SIZE = 15;
    private static final List<Employee> employeeList = new ArrayList<>();
    public String hello() {
        return "Добро пожаловать в базу данных сотрудников. В данном разделе вы можете добавить, найти, удалить сотрудника или вывести список всех сотрудников";
    }

    public Employee addEmloyee(String firstName, String lastName, int department, int salary) {
        if (employeeList.size()+1 > SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        String fullName = firstName + " " + lastName;
            Employee newEmployee = new Employee(firstName, lastName, department, salary);
            if(employeeList.stream()
                    .anyMatch(e -> fullName.equals(e.getFullName()))){
                    throw new EmployeeAlreadyAddedException(fullName);
            } else employeeList.add(newEmployee);
            return newEmployee;
    }

        public Employee removeEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        Employee removableEmployee = employeeList.stream()
                .filter(e -> fullName.equals(e.getFullName()))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(fullName));
        employeeList.remove(removableEmployee);
        return removableEmployee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return employeeList.stream()
                .filter(e -> fullName.equals(e.getFullName()))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(fullName));
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
