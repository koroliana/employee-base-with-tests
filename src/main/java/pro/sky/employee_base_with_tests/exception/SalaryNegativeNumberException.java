package pro.sky.employee_base_with_tests.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SalaryNegativeNumberException extends RuntimeException {
    private final int salary;

    public SalaryNegativeNumberException(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}
