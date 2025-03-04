package microservices1.candidate_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices1.candidate_service.model.Candidate;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidateServiceImpl implements CandidateService{

    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory factory;
    private final String urlExternalService = "http://localhost:8080/employee";

    @Override
    public List<Candidate> candidatesByPosition(String position) {
        CircuitBreaker circuit = factory.create("circuit1");
        return circuit.run(()->
                    Arrays.stream(restTemplate.getForObject(urlExternalService, Candidate[].class))
                    .filter(c -> c.getPosition().equals(position))
                    .collect(Collectors.toList()), //supplier (manage the external call and the return response)
                throwable ->{
                    log.error("External service ("+urlExternalService+") is failing.");
                    return List.of(new Candidate("Backup Candidate", 0, "")); //function fallback (in case of error..)
                });
    }
//    private List<Candidate> fallBackMethod(){
//        ResponseWrapper<List<Candidate>> response = new ResponseWrapper<List<Candidate>>();
//        response.setError("asd");
//        return response;
//    }
}
