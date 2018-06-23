package com.fission.recruitment.javatask;

import com.fission.recruitment.javatask.customannotation.Log;
import com.fission.recruitment.javatask.domain.Employee;
import com.fission.recruitment.javatask.domain.fexception.FDataCheckException;
import com.fission.recruitment.javatask.fileutility.FFileWriter;
import com.fission.recruitment.javatask.fileutility.Sort;
import org.slf4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

@ComponentScan(basePackages = "com.fission")
@Service
public class Main {

  @Log
  static Logger logger;

  public static void main(String[] args){
    Properties prop = new Properties();
    try {
      prop.load(new FileInputStream(args[0] + "/output.properties"));
    } catch (FileNotFoundException e) {
      logger.error("Unable to find the config file", e);
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
    File file = new File(prop.getProperty("EMPLOYEE_INPUT_FILE_LOCATION"));
    FFileWriter fileWriter = new FFileWriter(file);

    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("enter your  input :) ");
      String data = sc.nextLine();
      if (data.equalsIgnoreCase("SORT")) {
        new Thread(new Sort(file)).start();
      } else if (data.equalsIgnoreCase("EXIT")) {
        System.out.println("Terminating application :) ");
        System.exit(0);
      }else{
        try {
          String[] attributes = data.split(",");
          fileWriter.write(Employee.getEmployee(attributes));
        }catch (FDataCheckException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
