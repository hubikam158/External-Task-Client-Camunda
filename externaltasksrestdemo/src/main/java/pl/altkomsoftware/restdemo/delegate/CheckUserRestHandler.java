package pl.altkomsoftware.restdemo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.altkomsoftware.restdemo.utils.ConstValue;
import pl.altkomsoftware.restdemo.utils.RestCommunicator;

@Component
@ExternalTaskSubscription("userCheckRest")
@Slf4j
public class CheckUserRestHandler implements ExternalTaskHandler {

    @Autowired
    private RestCommunicator restCommunicator;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        String email = externalTask.getVariable("userEmail");

        String endpoint = ConstValue.SLASH + ConstValue.USERS + ConstValue.QUESTION_MARK +
                ConstValue.USER_EMAIL + ConstValue.EQUALS + email;
        String method = ConstValue.GET;

        Object[] resultArray = (Object[]) restCommunicator.executeRest(endpoint, method, null).getBody();
        assert resultArray != null;
        log.info(resultArray.length == 0 ? "User not registered" : "User registered");
        String answer = resultArray.length == 0 ? "NO" : "YES";
        log.info("result entity = {}", resultArray);

        VariableMap variables = Variables.createVariables();
        variables.put("userRegistered",answer);

        externalTaskService.complete(externalTask,variables);
    }
}
