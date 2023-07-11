package pl.altkomsoftware.restdemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class RestCommunicator {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${restapiuri}")
    private String REST_URI;

    public ResponseEntity<?> executeRest(String endpoint, String method, Map<String, Object> map) {

        ResponseEntity<?> response;
        Object entity;
        Object[] entities = new Object[1];
        if (ConstValue.GET.equals(method)) {
            response = restTemplate.getForEntity(REST_URI + endpoint, Object[].class);
            entities = (Object[]) Objects.requireNonNull(response.getBody());
        } else if (ConstValue.POST.equals(method)) {
            response = restTemplate.postForEntity(REST_URI + endpoint, map, Object.class);
            entity = response.getBody();
            entities[0] = entity;
        } else if (ConstValue.PUT.equals(method)) {
            HttpEntity<Object> requestEntity = new HttpEntity<>(map);
            response = restTemplate.exchange(REST_URI + endpoint, HttpMethod.PUT,requestEntity,Object.class);
            entity = response.getBody();
            entities[0] = entity;
        } else {
            throw new IllegalStateException("Unexpected method value: " + method);
        }

        return entities.length > 0 ?
                ResponseEntity.ok(entities) :
                ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
