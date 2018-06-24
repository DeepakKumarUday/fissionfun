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

  public static Employee getEmployeeForInputString(String data) throws FDataCheckException {
    String[] attributes = data.split(",");
    if (attributes.length != 5){
      throw new ArgumentCountMismatchException("data entered should have only 5 attributes OR SORT OR EXIT keyword.");
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

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getExperience() {
    return experience;
  }

  public int getAge() {
    return age;
  }

  public String getOrganisation() {
    return organisation;
  }
}
