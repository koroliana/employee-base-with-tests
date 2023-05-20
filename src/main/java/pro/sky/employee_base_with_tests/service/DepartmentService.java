package pro.sky.employee_base_with_tests.service;

import org.springframework.stereotype.Service;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.DepartmentNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
            return employeeService.getDepartmentBase(department);
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
            return employeeService.getEmployeeMap();
        /*
        Map<Integer, List<Employee>> baseByDepartment = EmployeeBase.getBase().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return baseByDepartment.toString();

         */
        }

        public int summarizeDepartmentSalaries(int department) {
                int sum = 0;
                List<Employee> departmentBase = employeeService.getDepartmentBase(department);
                for (Employee employee : departmentBase) {
                    sum = sum + employee.getSalary();
                }
                return sum;

        }

    }
