package microservices1.candidate_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Candidate {
    private String name;
    private int IDcard;
    private String position;
}
