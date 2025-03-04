package microservices1.clients.controller;

import microservices1.clients.model.Person;
import microservices1.clients.model.ResponseWrapper;
import microservices1.clients.service.ClientService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
@RestController
@RequestMapping("client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseWrapper<List<Person>> addPerson(@RequestBody Person person){
        try{
            clientService.callForNewContact(person);
            return new ResponseWrapper<>(clientService.callForContactList());
        }
        catch(HttpClientErrorException e) {
            return new ResponseWrapper<>(e.getMessage());
        }
    }

    @GetMapping
    public ResponseWrapper<List<Person>> getAllClients(){
        try{
            return new ResponseWrapper<>(clientService.callForContactList());
        }
        catch(HttpClientErrorException e) {
            return new ResponseWrapper<>(e.getMessage());
        }
    }

    @GetMapping(value = "/{age1}/{age2}")
    public ResponseWrapper<List<Person>> searchForAge(@PathVariable("age1") int age1, @PathVariable("age2") int age2){
        try{
            return new ResponseWrapper<>(clientService.searchClientForAge(age1, age2));
        }
        catch(HttpClientErrorException e) {
            return new ResponseWrapper<>(e.getMessage());
        }
    }
}
