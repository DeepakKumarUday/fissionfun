package com.fission.recruitment.javatask.fileutility;

import com.fission.recruitment.javatask.domain.Employee;
import com.fission.recruitment.javatask.domain.fexception.FDataCheckException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FFileReader {
  private File file;


  public FFileReader(File file) {
    this.file = file;
  }

  public int printEmployeeDetailsForCompany(String companyName){
    int counter = 0;
    try( BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String currentLine = reader.readLine();
      while (currentLine != null)
      {
        Employee employee ;
        try {
          employee = Employee.getEmployeeForInputString(currentLine);
          if(employee.getOrganisation().equalsIgnoreCase(companyName)){
            counter ++;
            System.out.println(counter +". " + employee.toString());
          }
        } catch (FDataCheckException e) {
          System.out.println("BAD DATA :" + e.getMessage());
        }
        currentLine = reader.readLine();
      }
      if(counter == 0 ){
        System.out.println("No record found for company : " + companyName);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return counter;
  }
}
