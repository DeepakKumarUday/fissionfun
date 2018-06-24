package com.fission.recruitment.javatask.domain.comparators;


import com.fission.recruitment.javatask.domain.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class EmployeeMultiComparator implements Comparator<Employee>{

  private List<Comparator<Employee>> comparators = new LinkedList<>();

  @SafeVarargs
  public EmployeeMultiComparator(Comparator<Employee>... comparators) {
    this.comparators = Arrays.asList(comparators);
  }

  @Override
  public int compare(Employee emp1, Employee emp2) {
    for (Comparator<Employee> comparator : comparators) {
      int result = comparator.compare(emp1, emp2);
      if (result != 0) {
        return result;
      }
    }
    return 0;
  }
}
