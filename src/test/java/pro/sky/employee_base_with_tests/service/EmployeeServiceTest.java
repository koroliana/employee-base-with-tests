package pro.sky.employee_base_with_tests.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.EmployeeAlreadyAddedException;
import pro.sky.employee_base_with_tests.exception.EmployeeNotFoundException;
import pro.sky.employee_base_with_tests.exception.EmployeeStorageIsFullException;

import java.util.stream.Stream;

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


    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmloyee("Елена", "Тышко", 8, 88000));
        }

    @Test
    public void removeTest() {
        int beforeCount = employeeService.getEmployeeList().size();
        Employee expected = new Employee("Алексей", "Ермаков", 2, 80000);

        Assertions.assertThat(employeeService.removeEmployee("Алексей", "Ермаков"))
                .isEqualTo(expected)
                .isNotIn(employeeService.getEmployeeList());
        Assertions.assertThat(employeeService.getEmployeeList()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("Вася", "Ложкин"));
    }

    @Test
    public void findTest() {
        int beforeCount = employeeService.getEmployeeList().size();
        Employee expected = new Employee("Евгения", "Добролюбова", 1, 50000);

        Assertions.assertThat(employeeService.findEmployee("Евгения", "Добролюбова"))
                .isEqualTo(expected)
                .isIn(employeeService.getEmployeeList());
        Assertions.assertThat(employeeService.getEmployeeList()).hasSize(beforeCount);
    }

    @Test
    public void findWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Вася", "Ложкин"));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(employeeService.getEmployeeList())
                .hasSize(8)
                .containsExactlyInAnyOrder(
                        new Employee("Василий", "Васичкин", 3, 150000),
                        new Employee("Василиса", "Прекрасная", 1, 100000),
                        new Employee("Алексей", "Ермаков", 2, 80000),
                        new Employee("Евгения", "Добролюбова", 1, 50000),
                        new Employee("Дмитрий", "Кривоногов", 1, 60000),
                        new Employee("Ивангай", "Третьяков", 2, 95000),
                        new Employee("Толгонак", "Слепаков", 3, 122000),
                        new Employee("Елена", "Тышко", 3, 73000)
                );
    }

    /*
    @Test
    public void addWhenStorageIsFullTest() {
        Stream.iterate(1, i -> i + 1)
                .limit(7)
                .map(number -> new Employee(
                                "Петя" + ((char) ('а' + number)),
                                "Петрович" + ((char) ('а' + number)),
                                number,
                                10_000 + number
                        )
                )
                .forEach(employee ->
                        employeeService.addEmloyee(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getDepartment(),
                                employee.getSalary()
                        )
                );

        Assertions.assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.addEmloyee("Пупок", "Пупкин", 10, 100000));
    }


     */

}
