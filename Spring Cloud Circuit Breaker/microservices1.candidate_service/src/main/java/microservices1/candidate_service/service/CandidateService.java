package microservices1.candidate_service.service;

import microservices1.candidate_service.model.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> candidatesByPosition(String position);
}
