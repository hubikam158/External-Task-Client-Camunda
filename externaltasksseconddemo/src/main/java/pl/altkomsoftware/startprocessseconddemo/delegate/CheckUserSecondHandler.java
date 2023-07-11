package pl.altkomsoftware.startprocessseconddemo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("userCheckNewCopy") // tworzymy subskrypcję dla tego topicu
@Slf4j
public class CheckUserSecondHandler implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        if (externalTask.getProcessDefinitionKey().equals("First_process")) {
            String firstName = externalTask.getVariable("firstName");
            String lastName = externalTask.getVariable("lastName");
            String email = externalTask.getVariable("userEmail");
            long age = externalTask.getVariable("age");
            String message = "";
            long ageInFiveYears = age + 5;

            log.info("First Process running...");
            log.info("firstname = {}, lastName = {}, email = {}, age = {}, message = {}", firstName, lastName, email, age, message);

            message = age < 18 ? "No way." : "Come in!";

            VariableMap variables = Variables.createVariables();
            variables.put("message", message);
            variables.put("ageInFiveYears", ageInFiveYears);

            externalTaskService.complete(externalTask, variables);
        } else if (externalTask.getProcessDefinitionKey().equals("Second_process")) {
            String nationality = externalTask.getVariable("nationality");
            String firstName = externalTask.getVariable("firstName");
            String lastName = externalTask.getVariable("lastName");
            log.info("Second Process running...");
            log.info("nationality = {}, name = {}, lastName = {}", nationality,firstName,lastName);
            VariableMap variables = Variables.createVariables();
            variables.put("MSG", "added nationality successfully");

            externalTaskService.complete(externalTask, variables);
        }
    }
}
