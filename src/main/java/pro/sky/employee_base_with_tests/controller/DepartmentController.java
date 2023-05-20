package pro.sky.employee_base_with_tests.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.DepartmentNotFoundException;
import pro.sky.employee_base_with_tests.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String hello() {
        return departmentService.hello();
    }


    @GetMapping (path = "/employees")
    public Map<Integer, List<Employee>> getEmployeeBase() {
        return departmentService.getEmployeeBase();
    }

    @GetMapping (path = "/{id}/employees")
    public List<Employee> printDepartmentBase(@PathVariable int id) {
        return departmentService.getDepartmentBase(id);
    }

    @GetMapping (path = "/{id}/salary/max")
    public Integer findMaxDepartmentSalary(@PathVariable int id) {
        return departmentService.findMaxDepartmentSalary(id);
    }

    @GetMapping (path = "/{id}/salary/min")
    public Integer findMinDepartmentSalary(@PathVariable int id) {
        return departmentService.findMinDepartmentSalary(id);
    }

    @GetMapping (path = "/{id}/salary/sum")
    public Integer summarizeDepartmentSalaries(@PathVariable int id) {
        return departmentService.summarizeDepartmentSalaries(id);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(DepartmentNotFoundException.class)
    public String departmentNotFoundExceptionHandler(DepartmentNotFoundException e) {
        return "В отделе " + e.getDepartment() + " нет сотрудников";
    }


}
