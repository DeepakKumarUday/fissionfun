package com.fission.recruitment.javatask.fileutility;

import com.fission.recruitment.javatask.domain.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FFileWriter {
  public FileWriter fileWriter;

  public FFileWriter(File file) {
    try {
      this.fileWriter = new FileWriter(file, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public FFileWriter(File file, boolean append) {
    try {
      this.fileWriter = new FileWriter(file, append);
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

  public void close(){
    try {
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
