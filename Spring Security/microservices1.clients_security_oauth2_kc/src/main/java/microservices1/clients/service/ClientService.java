package microservices1.clients.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices1.clients.model.AuthResponse;
import microservices1.clients.model.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.FailedLoginException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    // main service call variables
    private final RestTemplate restTemplate; //constructor injection via Lombok annotation RequiredArgsConstructor
    private HttpHeaders serviceHeaders;
    private final String serviceUrl = "http://localhost:8500";

    //keycloak call variables
    private final String CLIENT_ID = "login";
    private final String GRANT_TYPE = "password";
    private final String URL_KEYKLOAK = "http://localhost:8080/realms/Contacts/protocol/openid-connect/token";

    public void provideAuthentication(String username, String password) {
        serviceHeaders = new HttpHeaders();
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.add("Content-type", "application/x-www-form-urlencoded");

        MultiValueMap<String,String> authBody = new LinkedMultiValueMap<>();
        authBody.add("client_id", CLIENT_ID);
        authBody.add("username", username);
        authBody.add("password", password);
        authBody.add("grant_type", GRANT_TYPE);
        AuthResponse authResponse = restTemplate.exchange(URL_KEYKLOAK,
                                                            HttpMethod.POST,
                                                            new HttpEntity<MultiValueMap<String, String>>(authBody, authHeaders),
                                                            AuthResponse.class).getBody();
        // prepare the general service header with the access token
        serviceHeaders.add("Authorization", "Bearer "+ authResponse.getAccess_token());
    }
    public void callForNewContact(Person person){
        restTemplate.exchange(serviceUrl+"/contactList", HttpMethod.POST, new HttpEntity<>(person, serviceHeaders), Void.class);
    }

    public List<Person> callForContactList(){
        Person[] personArray = restTemplate.exchange(serviceUrl+"/contactList", HttpMethod.GET, new HttpEntity<>(serviceHeaders), Person[].class).getBody();
        return Arrays.asList(personArray);
    }

    public List<Person> searchClientForAge(int age1, int age2) {
        return callForContactList().stream()
                .filter(p -> p.getAge() >= age1 && p.getAge() <= age2)
                .collect(Collectors.toList());
    }

}
