package com.fission.recruitment.javatask.costomannotation;

import com.fission.recruitment.javatask.customannotation.Log;
import com.fission.recruitment.javatask.customannotation.beanbinding.ApplicationConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LogAnnotationTest {
  @Log()
  private static Logger logger;
  static {
    new AnnotationConfigApplicationContext(ApplicationConfig.class);
  }
  
  @Test
  public void testLogAnnotation(){

    logger.debug("i am running in here");
    assert logger.isInfoEnabled();
  }

}
