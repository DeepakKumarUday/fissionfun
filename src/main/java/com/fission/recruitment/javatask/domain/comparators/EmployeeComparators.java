package com.fission.recruitment.javatask.domain.comparators;

import com.fission.recruitment.javatask.domain.Employee;

import java.util.Comparator;

public class EmployeeComparators {

  public static Comparator<Employee> getAgeComparator() {
    return new Comparator<Employee>() {
      @Override
      public int compare(Employee employee, Employee e2) {
        return employee.getAge() - e2.getAge();
      }
    };
  }

  public static Comparator<Employee> getNameComparator() {
    return new Comparator<Employee>() {
      @Override
      public int compare(Employee e1, Employee e2) {
        return (e1.getFirstName() + e1.getLastName()).compareTo(e2.getFirstName() + e2.getLastName());
      }
    };
  }

  public static Comparator<Employee> getOrganisationComparator() {
    return new Comparator<Employee>() {
      @Override
      public int compare(Employee e1, Employee e2) {
        return e1.getOrganisation().compareTo(e2.getOrganisation());
      }
    };
  }

  public static Comparator<Employee> getExperienceComparator() {
    return new Comparator<Employee>() {
      @Override
      public int compare(Employee e1, Employee e2) {
        return e1.getExperience() - e2.getExperience();
      }
    };
  }

  public static Comparator<Employee> getExperienceAgeRatioComparator() {
    return new Comparator<Employee>() {
      @Override
      public int compare(Employee e1, Employee e2) {
        return e1.getExperience()/e1.getAge() - e2.getExperience()/e2.getAge();
      }
    };
  }
}
