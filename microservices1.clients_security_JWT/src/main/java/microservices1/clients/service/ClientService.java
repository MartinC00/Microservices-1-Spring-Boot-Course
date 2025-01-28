package microservices1.clients.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices1.clients.model.Person;
import microservices1.clients.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
    private HttpHeaders headers = new HttpHeaders();

    public void provideAuthentication(String username, String password){
        // make the call with the User in the body, and obtain the token
        String token = restTemplate.exchange(url + "/login", HttpMethod.POST, new HttpEntity<>(new User(username, password)), String.class).getBody();
        // prepare the header with the token
        headers.add("Authorization", "Bearer "+ token);
    }
    public void callForNewContact(Person person){
        restTemplate.exchange(url+"/contactList", HttpMethod.POST, new HttpEntity<>(person, headers), Void.class);
    }

    public List<Person> callForContactList(){
        Person[] personArray = restTemplate.exchange(url+"/contactList", HttpMethod.GET, new HttpEntity<>(headers), Person[].class).getBody();
        return Arrays.asList(personArray);
    }

    public List<Person> searchClientForAge(int age1, int age2) {
        return callForContactList().stream()
                .filter(p -> p.getAge() >= age1 && p.getAge() <= age2)
                .collect(Collectors.toList());
    }

}
