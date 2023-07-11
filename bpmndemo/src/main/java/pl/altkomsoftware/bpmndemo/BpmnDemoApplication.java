package pl.altkomsoftware.bpmndemo;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class BpmnDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpmnDemoApplication.class, args);
    }

}