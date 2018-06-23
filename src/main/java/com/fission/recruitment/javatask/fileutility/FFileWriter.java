package com.fission.recruitment.javatask.fileutility;

import com.fission.recruitment.javatask.domain.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FFileWriter {
  private FileWriter fileWriter;

  public FFileWriter(File file) {
    try {
      this.fileWriter = new FileWriter(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void write(Employee employee){
    try {
      fileWriter.write(employee.toString());
      fileWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
