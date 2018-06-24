package com.fission.recruitment.javatask.customannotation.beanbinding;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.fission")
public class ApplicationConfig {
}
