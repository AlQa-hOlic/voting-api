package in.alqaholic.VotingAPI.exceptions;

import org.springframework.http.HttpStatus;

public class CandidateDoesNotExist extends ApiException {
    public CandidateDoesNotExist() {
        super("Candidate not found!", HttpStatus.NOT_FOUND);
    }
}
