package com.fission.recruitment.domain;


import com.fission.recruitment.javatask.domain.DataValidator;
import com.fission.recruitment.javatask.domain.fexception.ArgumentDataTypeMisMatchException;
import org.junit.Test;

public class DataValidatorTest {

  @Test(expected = ArgumentDataTypeMisMatchException.class)
  public void experienceCanNotBeAlphaNumeric()  throws ArgumentDataTypeMisMatchException{
    String input = "deepak, kumar , 4yrs , 24, u2opia";
      DataValidator.isValidData(input.split(","));
  }

  @Test
  public void testValidDataArgument() throws ArgumentDataTypeMisMatchException {
    String input = "deepak, kumar , 4 , 24, u2opia";
      DataValidator.isValidData(input.split(","));
  }

  @Test(expected = ArgumentDataTypeMisMatchException.class)
  public void ageCannotBeZeroOrNegativeInteger() throws ArgumentDataTypeMisMatchException{
    String input = "deepak, kumar , 4 , 0, u2opia";
      DataValidator.isValidData(input.split(","));
  }

  @Test(expected = ArgumentDataTypeMisMatchException.class)
  public void nameMustHaveOnlyAlphabets() throws ArgumentDataTypeMisMatchException{
    String input = "d33pak, kumar , 4 , 0, u2opia";
    DataValidator.isValidData(input.split(","));
  }

  @Test(expected = ArgumentDataTypeMisMatchException.class)
  public void emptyFieldIsNotAllowed() throws ArgumentDataTypeMisMatchException{
    String input = "d33pak,  , 4 , 0, u2opia";
    DataValidator.isValidData(input.split(","));
  }

 }

