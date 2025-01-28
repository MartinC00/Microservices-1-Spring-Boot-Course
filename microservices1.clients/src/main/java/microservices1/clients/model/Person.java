package microservices1.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Person {
    private String name;
    private String email;
    private int age;
}
