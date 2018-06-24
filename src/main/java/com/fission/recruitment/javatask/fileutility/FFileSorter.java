package com.fission.recruitment.javatask.fileutility;

import com.fission.recruitment.javatask.FEmployeeDataProcessor;
import com.fission.recruitment.javatask.domain.Employee;
import com.fission.recruitment.javatask.domain.comparators.EmployeeComparators;
import com.fission.recruitment.javatask.domain.comparators.EmployeeMultiComparator;
import com.fission.recruitment.javatask.domain.fexception.FDataCheckException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class FFileSorter implements Callable{
  private File file;
  private List<Employee> employees = new LinkedList<>();
  boolean isSorting = true;

  public FFileSorter(File file) {
    this.file = file;
  }

  List<Employee> read(){
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String currentLine = reader.readLine();
      while (currentLine != null)
      {
        employees.add(Employee.getEmployeeForInputString(currentLine));
        currentLine = reader.readLine();
      }
      isSorting = false;
    }catch (FDataCheckException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
    return employees;
  }

  void sortFile(){
    read();
    employees.sort(new EmployeeMultiComparator(EmployeeComparators.getOrganisationComparator(),
      EmployeeComparators.getExperienceComparator(), EmployeeComparators.getNameComparator()));
    File file1 = new File(FEmployeeDataProcessor.properties.getProperty("FILE_PATH_FOR_ORG_EXP_NAME_SORT"));
    FFileWriter f1 = new FFileWriter(file1, false);
    for (Employee emp: employees
      ) {
      f1.write(emp);
    }
    f1.close();
    File file2 = new File(FEmployeeDataProcessor.properties.getProperty("FILE_PATH_FOR_AGE_EXP_RATIO_ORG_SORT"));
    FFileWriter f2 = new FFileWriter(file2, false);
    employees.sort(new EmployeeMultiComparator(EmployeeComparators.getExperienceAgeRatioComparator(),
      EmployeeComparators.getOrganisationComparator()));
    for (Employee emp: employees
      ) {
      f2.write(emp);
    }
    f2.close();
  }

  @Override
  public Object call() throws Exception {
    sortFile();
    return isSorting;
  }
}
