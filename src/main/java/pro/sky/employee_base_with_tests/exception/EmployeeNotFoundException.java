package pro.sky.employee_base_with_tests.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    private final String fullName;

    public EmployeeNotFoundException(String fullName) {
        this.fullName = fullName;
    }

    public String getEmployee() {
        return fullName;
    }

}
