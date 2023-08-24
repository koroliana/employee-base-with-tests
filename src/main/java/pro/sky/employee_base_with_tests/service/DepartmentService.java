package pro.sky.employee_base_with_tests.service;

import org.springframework.stereotype.Service;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.DepartmentNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
    public class DepartmentService {

        private final EmployeeService employeeService;

        public DepartmentService(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }
        public String hello() {
            return "Добро пожаловать в базу данных сотрудников. В данном разделе вы можете найти сотрудника с максимальной и минимальной зарплатой в отделе, вывести список всех сотрудников отдела или всех сотрудников по отделам";
        }

        public List<Employee> getDepartmentBase(int department) {
            employeeService.getEmployeeList().stream()
                    .filter(e -> e.getDepartment() == department)
                    .findAny()
                    .orElseThrow(() -> new DepartmentNotFoundException(department));

            return employeeService.getEmployeeList().stream()
                    .filter(employee -> employee.getDepartment()==department)
                    .collect(Collectors.toList());

        }

        public Integer findMinDepartmentSalary(int department) {
                Employee minDepartmentSalaryEmployee = employeeService.getEmployeeList().stream()
                        .filter(e -> e.getDepartment() == department)
                        .min(Comparator.comparingInt(Employee::getSalary))
                        .orElseThrow(() -> new DepartmentNotFoundException(department));
                return minDepartmentSalaryEmployee.getSalary();
        }

        public Integer findMaxDepartmentSalary(int department) {
                Employee maxDepartmentSalaryEmployee = employeeService.getEmployeeList().stream()
                        .filter(e -> e.getDepartment() == department)
                        .max(Comparator.comparingInt(Employee::getSalary))
                        .orElseThrow(() -> new DepartmentNotFoundException(department));
                return maxDepartmentSalaryEmployee.getSalary();


        }
        public Map<Integer, List <Employee>> getEmployeeBase() {
        return employeeService.getEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        }

        public int summarizeDepartmentSalaries(int department) {
            employeeService.getEmployeeList().stream()
                    .filter(e -> e.getDepartment() == department)
                    .findAny()
                    .orElseThrow(() -> new DepartmentNotFoundException(department));

            return employeeService.getEmployeeList().stream()
                    .filter(e -> e.getDepartment() == department)
                    .mapToInt(Employee::getSalary)
                    .sum();

        }


    }
