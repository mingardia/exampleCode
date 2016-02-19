package com.eis.cloud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by mingardia on 2/1/16.
 */
public class Employee {
    String firstName;
    String lastName;
    String title;
    int salary;
    Date hireDate;
    List<Department> departments;


    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public int getSalary() {

        return salary;
    }

    public void setSalary(int salary) {

        this.salary = salary;
    }

    public Date getHireDate() {

        return hireDate;
    }

    public void setHireDate(Date hireDate) {

        this.hireDate = hireDate;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public List<Department> getDepartments() {
        if (departments == null)
        {
            departments = new ArrayList<Department>();
        }
        return departments;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (salary != employee.salary) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (title != null ? !title.equals(employee.title) : employee.title != null) return false;
        if (hireDate != null ? !hireDate.equals(employee.hireDate) : employee.hireDate != null) return false;
        return departments != null ? departments.equals(employee.departments) : employee.departments == null;

    }

    @Override
    public int hashCode() {

        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (departments != null ? departments.hashCode() : 0);
        return result;
    }
}
