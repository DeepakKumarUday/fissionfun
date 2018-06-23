package com.fission.recruitment.javatask.domain;

import com.fission.recruitment.javatask.domain.fexception.ArgumentDataTypeMisMatchException;
import org.apache.commons.lang3.StringUtils;

public class DataValidator {

  public static boolean isValidData(String[] attributes) throws ArgumentDataTypeMisMatchException {
    boolean isValid = StringUtils.isAlpha(attributes[0].trim()) && StringUtils.isAlpha(attributes[1].trim())
      && StringUtils.isNumeric(attributes[2].trim()) && StringUtils.isNumeric(attributes[3].trim());
    if(!isValid){
      throw new ArgumentDataTypeMisMatchException("Wrong data types for parameter");
    }
    return isValid;
  }
}
