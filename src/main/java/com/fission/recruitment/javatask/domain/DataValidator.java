package com.fission.recruitment.javatask.domain;

import com.fission.recruitment.javatask.domain.fexception.ArgumentDataTypeMisMatchException;
import org.apache.commons.lang3.StringUtils;

public class DataValidator {

  public static void isValidData(String[] attributes) throws ArgumentDataTypeMisMatchException {
      checkIsEmpty(attributes);
      isValidName(attributes[0], attributes[1]);
      isValidMonthExperience(attributes[2]);
      isValidAge(attributes[3]);
  }

  private static void checkIsEmpty(String... args) throws ArgumentDataTypeMisMatchException {
    for (String arg : args) {
      if (arg.trim().isEmpty()) {
        throw new ArgumentDataTypeMisMatchException("parameter cannot be empty");
      }
    }
  }

  private static void isValidName(String firstName , String surname) throws ArgumentDataTypeMisMatchException {
    boolean isValid = StringUtils.isAlphaSpace(firstName) && StringUtils.isAlphaSpace(surname);
    if(!isValid){
      throw new ArgumentDataTypeMisMatchException("Wrong name : Must have only alphabets");
    }
  }

  private static void isValidAge(String ageStr) throws ArgumentDataTypeMisMatchException {
    try {
      int age = Integer.parseInt(ageStr.trim());
      if(age <= 0){
        throw new ArgumentDataTypeMisMatchException("Wrong age data : Must be an integer > 0 , we got - " + ageStr);
      }
    }catch (NumberFormatException nfe){
      throw new ArgumentDataTypeMisMatchException("Wrong age data type : Must be an integer > 0 , we got - " + ageStr);
    }

  }

  private static void isValidMonthExperience(String expStr) throws ArgumentDataTypeMisMatchException {
    boolean isValid = StringUtils.isNumericSpace(expStr);
    if(!isValid){
      throw new ArgumentDataTypeMisMatchException("Wrong experience data type : Must be an integer, we got -" + expStr);
    }
  }
}
