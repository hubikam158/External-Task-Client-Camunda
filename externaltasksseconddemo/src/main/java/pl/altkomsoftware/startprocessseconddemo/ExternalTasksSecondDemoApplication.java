package pl.altkomsoftware.startprocessseconddemo;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalTasksSecondDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalTasksSecondDemoApplication.class, args);
    }

}