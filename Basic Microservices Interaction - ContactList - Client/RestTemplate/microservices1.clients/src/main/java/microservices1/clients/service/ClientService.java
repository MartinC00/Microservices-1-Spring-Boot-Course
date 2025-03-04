package microservices1.clients.service;

import lombok.RequiredArgsConstructor;
import microservices1.clients.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final RestTemplate restTemplate; //constructor injection via Lombok annotation RequiredArgsConstructor
    private final String url = "http://localhost:8080";

    public void callForNewContact(Person person){
        restTemplate.postForLocation(url+"/contactList", person);
    }

    public List<Person> callForContactList(){
        Person[] personArray = restTemplate.getForObject(url+"/contactList", Person[].class);
        return Arrays.asList(personArray);
    }

    public List<Person> searchClientForAge(int age1, int age2) {
        return callForContactList().stream()
                .filter(p -> p.getAge() >= age1 && p.getAge() <= age2)
                .collect(Collectors.toList());
    }
}
