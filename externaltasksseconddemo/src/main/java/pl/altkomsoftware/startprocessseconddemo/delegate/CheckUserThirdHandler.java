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
@ExternalTaskSubscription("userCheckOneMoreNewCopy")
@Slf4j
public class CheckUserThirdHandler implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        long age = externalTask.getVariable("age");
        log.info("age = {}",age);

        String message = age > 65 ? "Special discount." : "No special offer.";

        VariableMap variables = Variables.createVariables();
        variables.put("newMessage",message);

        externalTaskService.complete(externalTask,variables);
    }
}
