package com.fission.recruitment.javatask.domain;

import com.fission.recruitment.javatask.domain.fexception.ArgumentCountMismatchException;
import com.fission.recruitment.javatask.domain.fexception.FDataCheckException;

import java.util.Comparator;

public class Employee {
  private String firstName;
  private String lastName;
  private int experience;
  private int age;
  private String organisation;

  private Employee(String firstName, String lastName, int experience, int age, String organisation) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.experience = experience;
    this.age = age;
    this.organisation = organisation;
  }

  public static Employee getEmployee(String[] attributes) throws FDataCheckException {
    if (attributes.length != 5){
      throw new ArgumentCountMismatchException("data entered should have only 5 attributes ");
    }
    DataValidator.isValidData(attributes);
    int experience =  Integer.parseInt(attributes[2].trim());
    int age = Integer.parseInt(attributes[3].trim());
    return new Employee(attributes[0].trim(), attributes[1].trim(), experience, age, attributes[4].trim());
  }

  @Override
  public String toString() {
    return firstName + ", " +lastName + ", " + experience + ", " + age + ", " +
      organisation + System.getProperty("line.separator");
  }

  public static Comparator<Employee> getSortByOrgExpNameComparator() {
    return new Comparator<Employee>() {
      @Override
      public int compare(Employee employee, Employee t1) {
        return employee.organisation.compareTo(t1.organisation);
      }
      // compare using attribute 1
    };
  }

  public static Comparator<Employee> getSortByExpAgeRatioAndOrgComparator() {
    return new Comparator<Employee>() {
      @Override
      public int compare(Employee employee, Employee t1) {
        return (employee.firstName+employee.lastName).compareTo(t1.firstName+t1.lastName);
      }
      // compare using attribute 2
    };
  }
}
