package microservices1.clients.controller;

import microservices1.clients.model.Person;
import microservices1.clients.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public List<Person> addPerson(@RequestBody Person person){
        CompletableFuture<List<Person>> personList = clientService.callForNewContact(person);
        clientService.doSomethingElse();

        try {
            return personList.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
