package in.alqaholic.VotingAPI.exceptions;

import org.springframework.http.HttpStatus;

public class CandidateAlreadyExists extends ApiException {
    public CandidateAlreadyExists() {
        super("Candidate already exists", HttpStatus.CONFLICT);
    }
}
