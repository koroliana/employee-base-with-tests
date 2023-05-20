package pro.sky.employee_base_with_tests.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {
   private final  int department;

    public DepartmentNotFoundException(int department) {
        this.department = department;
    }

    public int getDepartment() {
        return department;
    }

}
