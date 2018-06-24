package com.fission.recruitment.domain.fileutility;

import com.fission.recruitment.javatask.fileutility.FFileReader;
import org.junit.Test;

import java.io.File;


public class FFileReaderTest {
  private File dataFile = new File(getClass().getResource("/employees_datafile" ).toString());

  @Test
  public void getEmployessOfFission(){
    FFileReader fFileReader = new FFileReader(dataFile);
    assert 4 == fFileReader.printEmployeeDetailsForCompany("fission");
  }

}
