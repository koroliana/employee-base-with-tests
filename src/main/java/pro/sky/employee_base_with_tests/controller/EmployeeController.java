package pro.sky.employee_base_with_tests.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.*;
import pro.sky.employee_base_with_tests.service.EmployeeService;
import java.util.List;

@RestController
    @RequestMapping("/employee")
    public class EmployeeController {
        private final EmployeeService employeeService;

        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        private boolean isNull(String a, String b){
            return a == null || b == null;
        }
        private boolean isNull(String a, String b, Integer c){
            return a == null || b == null || c == null;
        }
        private boolean isNull(String a, String b, Integer c, Integer d){
            return a == null || b == null || c == null || d == null;
        }

        @GetMapping
        public String hello() {
            return employeeService.hello();
        }

        @GetMapping (path = "/add")
        public String addEmloyee(@RequestParam(required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) Integer department, @RequestParam(required = false) Integer salary) {
            if (isNull(name, lastname, department, salary)) {
                return "Для добавления сотрудника введите имя, фамилию, отдел и зарплату. Отдел и зарплата должны быть целыми числами";
            }
            else {
                Employee newEmployee = employeeService.addEmloyee(name, lastname, department, salary);
                return "Новый сотрудник " + newEmployee + " добавлен";
            }
        }


        @GetMapping (path = "/find")
        public String findEmloyee(@RequestParam String name, @RequestParam String lastname) {
            Employee foundEmployee = employeeService.findEmployee(name, lastname);
            return "Сотрудник " + foundEmployee + " найден";
        }

        @GetMapping (path = "/delete")
        public String removeEmloyee(@RequestParam String name, @RequestParam String lastname) {
            Employee removableEmployee = employeeService.removeEmployee(name, lastname);
            return "Сотрудник "+ removableEmployee + " удален";
        }

        @GetMapping (path = "/base")
        public List<Employee> getBase() {
            return employeeService.getEmployeeList();
        }



        @ExceptionHandler(EmployeeNotFoundException.class)
        public String employeeNotFoundExceptionHandler(EmployeeNotFoundException e) {
            return "Сотрудник "+ e.getEmployee()+" не найден";
        }

        @ExceptionHandler(EmployeeAlreadyAddedException.class)
        public String employeeAlreadyAddedExceptionHandler(EmployeeAlreadyAddedException e) {
            return "Сотрудник "+ e.getFullName()+" уже добавлен";
        }

        @ExceptionHandler(EmployeeStorageIsFullException.class)
        public String employeeStorageIsFullExceptionHandler(EmployeeStorageIsFullException e) {
            return "Все позиции заняты. Удалите сотрудника, чтобы добавить";
        }

        @ExceptionHandler(SalaryNegativeNumberException.class)
        public String salaryNegativeNumberExceptionHandler(SalaryNegativeNumberException e) {
            return "Зарплата в компании должна быть больше нуля! А вы ввели " +e.getSalary();
        }

}

