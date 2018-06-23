package com.fission.recruitment.domain;


import com.fission.recruitment.javatask.domain.DataValidator;
import com.fission.recruitment.javatask.domain.fexception.ArgumentDataTypeMisMatchException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class DataValidatorSpec {

  @Test
  public void testArgumentDataTypeMisMatchException() {
    boolean thrown = false;
    String input = "deepak, kumar , 4yrs , 24, u2opia";
    try {
      DataValidator.isValidData(input.split(","));
    } catch (ArgumentDataTypeMisMatchException e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  public void testValidDataArgument() {
    boolean valid = true;
    String input = "deepak, kumar , 4 , 24, u2opia";
    try {
      DataValidator.isValidData(input.split(","));
    } catch (ArgumentDataTypeMisMatchException e) {
      valid = false;
    }
    assertTrue(valid);
  }

 }
