package com.fission.recruitment.javatask;

import com.fission.recruitment.javatask.customannotation.Log;
import com.fission.recruitment.javatask.customannotation.beanbinding.ApplicationConfig;
import com.fission.recruitment.javatask.domain.Employee;
import com.fission.recruitment.javatask.domain.fexception.FDataCheckException;
import com.fission.recruitment.javatask.fileutility.FFileReader;
import com.fission.recruitment.javatask.fileutility.FFileWriter;
import com.fission.recruitment.javatask.fileutility.FFileSorter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class FEmployeeDataProcessor {

  @Log
  private static Logger logger;
  static {
    new AnnotationConfigApplicationContext(ApplicationConfig.class);
  }

  private static List<Employee> bufferedEmployeeData  = new LinkedList<>();
  private static FFileWriter empFileWriter;
  private static File rawEmpFile;
  private static boolean isSorting = false;
  public static Properties properties = new Properties();

  public static void main(String[] args){
    CommandLine commandLine = readCli(args);
    String confDir = commandLine.getOptionValue("confDir");
    logger.info("config path is : " + confDir);
    try {
      properties.load(new FileInputStream(confDir + "/config.properties"));
      rawEmpFile = new File(properties.getProperty("EMPLOYEE_INPUT_FILE_LOCATION"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if(commandLine.hasOption("r")){
      Scanner sc = new Scanner(System.in);
      logger.info("reading data for company : " + confDir);
      System.out.println("Please enter the company name :");
      String companyName = sc.next();
      logger.info("reading data for company : " + companyName);
      FFileReader fFileReader = new FFileReader(rawEmpFile);
      fFileReader.printEmployeeDetailsForCompany(companyName);
    }else{
      empFileWriter = new FFileWriter(rawEmpFile, true);
      Scanner sc = new Scanner(System.in);

      while (true) {
        System.out.println("enter your  input :) ");
        String input = sc.nextLine();
        logger.info("writing data to file : " + input);
        processInput(input);
      }
    }
  }

  private static void processInput(String input){
    if (input.equalsIgnoreCase("SORT")) {
      logger.info("sorting data : ");
      empFileWriter.close();
      empFileWriter = null;
      ExecutorService service =  Executors.newSingleThreadExecutor();
      FFileSorter sumTask = new FFileSorter(rawEmpFile);
      Future<Boolean> future = service.submit(sumTask);
      try {
        isSorting = future.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    } else if (input.equalsIgnoreCase("EXIT")) {
      logger.info("Terminating application :) ");
      System.exit(0);
    }else{
      try {
        putDataInFile(input);
      } catch (FDataCheckException e) {
        e.printStackTrace();
      }
    }
  }

  private static void putDataInFile(String data) throws FDataCheckException {
    if(empFileWriter == null){
      empFileWriter = new FFileWriter(rawEmpFile);
    }
    Employee employee  = Employee.getEmployeeForInputString(data);
    if(isSorting){
      bufferedEmployeeData.add(employee);
    }else {
      for (Employee e: bufferedEmployeeData
        ) {
        empFileWriter.write(e);
        bufferedEmployeeData.remove(e);
      }
      empFileWriter.write(employee);
    }
  }

  private static CommandLine readCli(String[] args) {
    Options options = new Options();
    Option input = new Option("w", "write", false, "insert or sort data");
    input.setRequired(false);
    input.setType(String.class);
    options.addOption(input);

    Option output = new Option("r", "getEmployeesForCompany", false, "fetch data from file");
    output.setRequired(false);
    output.setType(String.class);
    options.addOption(output);

    Option config = new Option("c", "confDir", true, "fetch data from file");
    config.setRequired(true);
    config.setType(String.class);
    options.addOption(config);

    CommandLine cmd = null;
    try {
      cmd = new DefaultParser().parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      new HelpFormatter().printHelp("utility-name", options);

      System.exit(1);
    }
    return cmd;
  }

}
