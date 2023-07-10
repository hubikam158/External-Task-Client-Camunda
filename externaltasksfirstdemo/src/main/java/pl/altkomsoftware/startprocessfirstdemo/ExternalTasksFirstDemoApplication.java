package pl.altkomsoftware.startprocessfirstdemo;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalTasksFirstDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalTasksFirstDemoApplication.class, args);
    }

}