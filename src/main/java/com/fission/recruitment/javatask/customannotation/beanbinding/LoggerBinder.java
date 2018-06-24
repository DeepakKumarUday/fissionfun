package com.fission.recruitment.javatask.customannotation.beanbinding;

import ch.qos.logback.core.rolling.RollingFileAppender;
import com.fission.recruitment.javatask.customannotation.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class LoggerBinder implements BeanPostProcessor {
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
    ReflectionUtils.doWithFields(bean.getClass(), field -> {
      ReflectionUtils.makeAccessible(field);
      if (field.getAnnotation(Log.class) != null) {
        ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(bean.getClass());
        /*
        * trying to add file appender to enable logging in a file
        * */
        RollingFileAppender rollingFileAppender = new RollingFileAppender<>();
        log.addAppender(rollingFileAppender);
        field.set(bean, log);
      }
    });
    return bean;
  }
}