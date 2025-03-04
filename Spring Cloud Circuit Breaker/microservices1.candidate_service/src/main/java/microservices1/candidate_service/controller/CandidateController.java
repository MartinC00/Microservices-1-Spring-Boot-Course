package microservices1.candidate_service.controller;

import lombok.RequiredArgsConstructor;
import microservices1.candidate_service.model.Candidate;
import microservices1.candidate_service.service.CandidateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("candidate")
public class CandidateController {
    private final CandidateService candidateService;

    @GetMapping(value = "/{position}")
    public List<Candidate> candidates (@PathVariable("position") String position){
        return candidateService.candidatesByPosition(position);
    }
}
