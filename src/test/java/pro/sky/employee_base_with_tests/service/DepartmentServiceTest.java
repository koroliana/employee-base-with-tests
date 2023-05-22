package pro.sky.employee_base_with_tests.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employee_base_with_tests.Employee;
import pro.sky.employee_base_with_tests.exception.DepartmentNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    public static Stream<Arguments> findMinDepartmentSalaryTestParams() {
        return Stream.of(
                Arguments.of("1", 50000),
                Arguments.of("2",80000),
                Arguments.of("3",73000)
        );
    }

    public static Stream<Arguments> findMaxDepartmentSalaryTestParams() {
        return Stream.of(
                Arguments.of("1", 100000),
                Arguments.of("2", 95000),
                Arguments.of("3", 150000)
        );
    }

    public static Stream<Arguments> summarizeDepartmentSalariesTestParams() {
        return Stream.of(
                Arguments.of("1", 210000),
                Arguments.of("2", 175000),
                Arguments.of("3", 345000)
        );
    }

    public static Stream<Arguments> getDepartmentBaseTestParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Василиса", "Прекрасная", 1, 100000),
                                new Employee("Евгения", "Добролюбова", 1, 50000),
                                new Employee("Дмитрий", "Кривоногов", 1, 60000)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Алексей", "Ермаков", 2, 80000),
                                new Employee("Ивангай", "Третьяков", 2, 95000)
                        )
                ),
                Arguments.of(
                        3,
                        List.of(
                                new Employee("Василий", "Васичкин", 3, 150000),
                                new Employee("Толгонак", "Слепаков", 3, 122000),
                                new Employee("Елена", "Тышко", 3, 73000)
                        )
                )
        );
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.getEmployeeList()).thenReturn(
            List.of(
            new Employee("Василий", "Васичкин", 3, 150000),
            new Employee("Василиса", "Прекрасная", 1, 100000),
            new Employee("Алексей", "Ермаков", 2, 80000),
            new Employee("Евгения", "Добролюбова", 1, 50000),
            new Employee("Дмитрий", "Кривоногов", 1, 60000),
            new Employee("Ивангай", "Третьяков", 2, 95000),
            new Employee("Толгонак", "Слепаков", 3, 122000),
            new Employee("Елена", "Тышко", 3, 73000)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findMinDepartmentSalaryTestParams")
    public void findMinDepartmentSalaryTest(int department, int expected) {
        Assertions.assertThat(departmentService.findMinDepartmentSalary(department))
                .isEqualTo(expected);
    }

    @Test
    public void findMinDepartmentSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.findMinDepartmentSalary(0));
    }

    @ParameterizedTest
    @MethodSource("findMaxDepartmentSalaryTestParams")
    public void findMaxDepartmentSalaryTest(int department, int expected) {
        Assertions.assertThat(departmentService.findMaxDepartmentSalary(department))
                .isEqualTo(expected);
    }

    @Test
    public void findMaxDepartmentSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.findMaxDepartmentSalary(0));
    }

    @ParameterizedTest
    @MethodSource("summarizeDepartmentSalariesTestParams")
    public void summarizeDepartmentSalariesTest(int department, int expected) {
        Assertions.assertThat(departmentService.summarizeDepartmentSalaries(department))
                .isEqualTo(expected);
    }

    @Test
    public void summarizeDepartmentSalariesWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.summarizeDepartmentSalaries(0));
    }

    @ParameterizedTest
    @MethodSource("getDepartmentBaseTestParams")
    public void getDepartmentBaseTest(int department, List<Employee> expected) {
        Assertions.assertThat(departmentService.getDepartmentBase(department))
                .isEqualTo(expected);
    }

    @Test
    public void getDepartmentBaseWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.getDepartmentBase(0));
    }

    @Test
    public void getEmployeeBaseTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                    new Employee("Василиса", "Прекрасная", 1, 100000),
                    new Employee("Евгения", "Добролюбова", 1, 50000),
                    new Employee("Дмитрий", "Кривоногов", 1, 60000)
                ),
                2,
                List.of(
                        new Employee("Алексей", "Ермаков", 2, 80000),
                        new Employee("Ивангай", "Третьяков", 2, 95000)
                ),
                3,
                List.of(
                        new Employee("Василий", "Васичкин", 3, 150000),
                        new Employee("Толгонак", "Слепаков", 3, 122000),
                        new Employee("Елена", "Тышко", 3, 73000)
        ));
        Assertions.assertThat(departmentService.getEmployeeBase())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }

    /*
    @Test
    public void helloTest() {
        String expected = "Добро пожаловать в базу данных сотрудников. В данном разделе вы можете найти сотрудника с максимальной и минимальной зарплатой в отделе, вывести список всех сотрудников отдела или всех сотрудников по отделам";
        Assertions.assertThat(departmentService.hello())
                .isEqualTo(expected);
    }

     */



}
