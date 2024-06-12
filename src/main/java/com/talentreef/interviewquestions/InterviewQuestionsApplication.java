package com.talentreef.interviewquestions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.talentreef")
@EntityScan(basePackages = "com.talentreef")
public class InterviewQuestionsApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterviewQuestionsApplication.class, args);
  }

}
