# External-Task-Client-Camunda
The usage of External Task Client with Camunda

Aby poprawnie użyć External Task:
1. Tworzymy External Taska jako aplikację Spring Bootową.
2. Dodajemy dependencję:

<dependency>
  <groupId>org.camunda.bpm.springboot</groupId>
  <artifactId>camunda-bpm-spring-boot-starter-external-task-client</artifactId>
  <version>7.19.0</version>
</dependency>

3. Tworzymy klasę - handlera, implementując ExternalTaskHandler, dodajemy adnotacje @Component i @ExternalTaskSubscription("<nazwa-topicu>").
4. W metodzie execute dodajemy logikę biznesową, analogicznie do delegatek.
5. w application.yaml dodajemy:

camunda:
  bpm:
    client:
      base-url: http://localhost:8081/engine-rest # uri uderzające na Camundę
      lock-duration: 10000 # czas w [ms], przez który External Tasks są zablokowane zanim będą mogły być sfetchowane ponownie
      subscriptions:
        userCheck: # nazwa topicu
          variable-names: firstName, lastName, userEmail, age # fetchujemy tylko te variablasy
          process-definition-key: First_process # nazwa procesu, który startujemy w Camundzie

6. W Camundzie na wykresie w Service Tasku wybieramy Implementation = External oraz Topic = <nazwa-topicu>, gdzie nazwa topicu musi być zbieżna z użytą w handlerze oraz application.yaml.
7. Ewentualne dodatkowe opcje można doczytać tutaj: https://docs.camunda.org/manual/7.19/user-guide/ext-client/spring-boot-starter/
