package pro.sky.employee_base_with_tests.service;

import org.springframework.stereotype.Service;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.DepartmentNotFoundException;
import pro.sky.employee_base_with_tests.exception.EmployeeAlreadyAddedException;
import pro.sky.employee_base_with_tests.exception.EmployeeNotFoundException;
import pro.sky.employee_base_with_tests.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    private static final int SIZE = 15;
    private static final List<Employee> employeeList = new ArrayList<>();
    private static final Map<Integer, List <Employee>> employeeMap = new HashMap<>();

    public String hello() {
        return "Добро пожаловать в базу данных сотрудников. В данном разделе вы можете добавить, найти, удалить сотрудника или вывести список всех сотрудников";
    }

    public Employee addEmloyee(String firstName, String lastName, int department, int salary) {
        /*
        if (employeeList.size()+1 > SIZE) {
            throw new EmployeeStorageIsFullException();
        }

         */
            Employee newEmployee = new Employee(firstName, lastName, department, salary);
            for (Employee employee: employeeList) {
                if (newEmployee.equals(employee)) {
                    throw new EmployeeAlreadyAddedException(newEmployee);
                }
            }
            employeeList.add(newEmployee);

            if (employeeMap.containsKey(newEmployee.getDepartment())) {
                    employeeMap.get(newEmployee.getDepartment()).add(newEmployee);
                }
                else {
                    ArrayList<Employee> newDepartmentBase = new ArrayList<>();
                    newDepartmentBase.add(newEmployee);
                    employeeMap.put(newEmployee.getDepartment(),newDepartmentBase);
                }



            return newEmployee;
    }

    public static Employee findEmployeeByFullname(String fullName) {
        Employee foundEmployee = null;
        for (Employee employee: employeeList) {
            if(employee.getFullName().equals(fullName)){
                foundEmployee = employee;
            }
        }
        if (foundEmployee == null) {
            throw new EmployeeNotFoundException(fullName);
        }
        return foundEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        Employee removableEmployee = findEmployeeByFullname(fullName);
        employeeList.remove(removableEmployee);
        int department = removableEmployee.getDepartment();
        employeeMap.get(department).remove(removableEmployee);
        return removableEmployee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        Employee foundEmployee = findEmployeeByFullname(fullName);
        return foundEmployee;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public static Map<Integer, List <Employee>> getEmployeeMap() {
        return employeeMap;
    }

    public static List getDepartmentBase(int department) {
        if (employeeMap.containsKey(department)) {
            return employeeMap.get(department);
        } else throw new DepartmentNotFoundException(department);
    }
}
