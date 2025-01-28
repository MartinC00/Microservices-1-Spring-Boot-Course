package microservices1.clients.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices1.clients.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final RestTemplate restTemplate; //constructor injection via Lombok annotation RequiredArgsConstructor
    private final String url = "http://localhost:8080";

    @Async
    public CompletableFuture<List<Person>> callForNewContact(Person person){
        restTemplate.postForLocation(url+"/contactList", person);
        return CompletableFuture.completedFuture(callForContactList());
    }

    public List<Person> callForContactList(){
        Person[] personArray = restTemplate.getForObject(url+"/contactList", Person[].class);
        return Arrays.asList(personArray);
    }

    public void doSomethingElse() {
        IntStream.range(0, 50).forEach(i -> {
            try {
                Thread.sleep(10); //simulate other processes
                log.info("doing something else...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}