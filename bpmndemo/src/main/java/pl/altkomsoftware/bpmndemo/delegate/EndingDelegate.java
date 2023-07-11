package pl.altkomsoftware.bpmndemo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EndingDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String message = (String) delegateExecution.getVariable("message");
        Long ageInFiveYears = (Long) delegateExecution.getVariable("ageInFiveYears");

        log.info("message = {}, ageInFiveYears = {}",message,ageInFiveYears);
    }
}
