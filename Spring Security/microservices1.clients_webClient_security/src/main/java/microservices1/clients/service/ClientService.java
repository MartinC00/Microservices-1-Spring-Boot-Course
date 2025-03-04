package microservices1.clients.service;

import microservices1.clients.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
public interface ClientService {
    List<Person> callForContactList();
    List<Person> searchClientForAge(int age1, int age2);
    void callForNewContact(Person person);
}
