package pro.sky.employee_base_with_tests;

import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sky.employee_base_with_tests.exception.SalaryNegativeNumberException;

import java.util.Objects;

public class Employee {
    @JsonProperty("name")
    private final String firstName;
    @JsonProperty("lastname")
    private final String lastName;
    private int department;
    private int salary;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public Employee(String firstName, String lastName, int department, int salary) {
        if (salary <= 0) {
            throw new SalaryNegativeNumberException(salary);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;

    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Отдел: " + department + ", зарплата: "+ salary + ")";
    }
    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Employee employee = (Employee) other;
        return (firstName.equals(employee.firstName)&&lastName.equals(employee.lastName)
                &&department==employee.getDepartment()&&salary==employee.getSalary());
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
