package com.fission.recruitment.javatask.fileutility;

import java.io.File;

public class Sort implements Runnable{
  private File file;

  public Sort(File file) {
    this.file = file;
  }

  @Override
  public void run() {
      FFileReader fFileReader = new FFileReader(file);
      fFileReader.sortFile();
  }
}
