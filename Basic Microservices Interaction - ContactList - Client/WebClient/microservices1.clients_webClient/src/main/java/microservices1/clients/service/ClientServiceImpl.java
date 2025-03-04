package microservices1.clients.service;

import lombok.RequiredArgsConstructor;
import microservices1.clients.model.Person;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final WebClient webClient; //constructor injection via Lombok annotation RequiredArgsConstructor
    private final String url = "http://localhost:8080";

    public void callForNewContact(Person person){
        webClient.post()
                .uri(url+"/contactList")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(person)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public List<Person> callForContactList(){
        Person[] personArray =
                webClient.get()
                        .uri(url+"/contactList")
                        .retrieve()
                        .bodyToMono(Person[].class)
                        .block();

        return Arrays.asList(personArray);
    }

    public List<Person> searchClientForAge(int age1, int age2) {
        return callForContactList().stream()
                .filter(p -> p.getAge() >= age1 && p.getAge() <= age2)
                .collect(Collectors.toList());
    }
}
