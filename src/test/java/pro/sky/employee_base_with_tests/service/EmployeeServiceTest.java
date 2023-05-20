package pro.sky.employee_base_with_tests.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.employee_base_with_tests.Employee;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    public void beforeEach() {
        employeeService.addEmloyee("Василий", "Васичкин", 3, 150000);
        employeeService.addEmloyee("Василиса", "Прекрасная", 1, 100000);
        employeeService.addEmloyee("Алексей", "Ермаков", 2, 80000);
        employeeService.addEmloyee("Евгения", "Добролюбова", 1, 50000);
        employeeService.addEmloyee("Дмитрий", "Кривоногов", 1, 60000);
        employeeService.addEmloyee("Ивангай", "Третьяков", 2, 95000);
        employeeService.addEmloyee("Толгонак", "Слепаков", 3, 122000);
        employeeService.addEmloyee("Елена", "Тышко", 3, 73000);
    }

/*
    @AfterEach
    public void afterEach() {
        employeeService.getEmployeeList()
                .forEach(employee -> employeeService.removeEmployee(employee.getFirstName(), employee.getLastName()));

    }

 */

    @Test
    public void addTest() {
        int beforeCount = employeeService.getEmployeeList().size();
        Employee expected = new Employee("Алексей", "Тыгыдык", 3, 80000);

        Assertions.assertThat(employeeService.addEmloyee("Алексей", "Тыгыдык", 3, 80000))
                .isEqualTo(expected)
                .isIn(employeeService.getEmployeeList());
        Assertions.assertThat(employeeService.getEmployeeList()).hasSize(beforeCount + 1);
        Assertions.assertThat(employeeService.findEmployee("Алексей", "Тыгыдык")).isEqualTo(expected);


    }


}
