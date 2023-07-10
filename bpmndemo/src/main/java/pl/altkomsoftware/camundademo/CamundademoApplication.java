package pl.altkomsoftware.camundademo;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableProcessApplication
public class CamundademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundademoApplication.class, args);
    }

}