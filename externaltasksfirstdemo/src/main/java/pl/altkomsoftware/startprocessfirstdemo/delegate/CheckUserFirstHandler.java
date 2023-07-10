package pl.altkomsoftware.startprocessfirstdemo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("userCheck")
@Slf4j
public class CheckUserFirstHandler implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        String firstName = externalTask.getVariable("firstName");
        String lastName = externalTask.getVariable("lastName");
        String email = externalTask.getVariable("userEmail");
        long age = externalTask.getVariable("age");
        String message = "";
        long ageInFiveYears = age + 5;

        log.info("firstname = {}, lastName = {}, email = {}, age = {}, message = {}",firstName,lastName,email,age,message);

        message = age < 18 ? "You are too young to enter the event." : "Let's go, man!";

        VariableMap variables = Variables.createVariables();
        variables.put("message",message);
        variables.put("ageInFiveYears",ageInFiveYears);

        externalTaskService.complete(externalTask,variables);
    }
}
