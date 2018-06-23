package com.fission.recruitment.javatask.fileutility;

import com.fission.recruitment.javatask.domain.Employee;
import com.fission.recruitment.javatask.domain.fexception.FDataCheckException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FFileReader {
  private File file;
  private List<Employee> employees = new ArrayList<>();

  public FFileReader(File file) {
    this.file = file;
  }

  void read(){
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String currentLine = reader.readLine();
      while (currentLine != null)
      {
        employees.add(Employee.getEmployee(currentLine.split(",")));
        currentLine = reader.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (FDataCheckException e) {
      e.printStackTrace();
    }
  }

  public void sortFile(){
    read();
    employees.sort(Employee.getSortByOrgExpNameComparator());
    FFileWriter f1 = new FFileWriter(new File("/home/local/DATAMETICA/deepak.kumar/Downloads/e_test/sort_1"));
    for (Employee emp: employees
         ) {
      f1.write(emp);
    }

    FFileWriter f2 = new FFileWriter(new File("/home/local/DATAMETICA/deepak.kumar/Downloads/e_test/sort_2"));
    employees.sort(Employee.getSortByExpAgeRatioAndOrgComparator());
    for (Employee emp: employees
      ) {
      f2.write(emp);
    }
  }
}
