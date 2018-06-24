package com.fission.recruitment.javatask.fileutility;

import org.junit.Test;

import java.io.File;


public class FFileReaderTest {
  private File dataFile = new File(getClass().getResource("/data/employees_datafile").getFile());

  @Test
  public void getEmployessOfFission(){
    FFileReader fFileReader = new FFileReader(dataFile);
    assert 3 == fFileReader.printEmployeeDetailsForCompany("fission");
  }

  @Test
  public void getEmployessOfDatametica(){
    FFileReader fFileReader = new FFileReader(dataFile);
    assert 1 == fFileReader.printEmployeeDetailsForCompany("DATAMETICA");
  }

  @Test
  public void getEmployessOfUnknowCompany(){
    FFileReader fFileReader = new FFileReader(dataFile);
    assert 0 == fFileReader.printEmployeeDetailsForCompany("unknown");
  }

}
