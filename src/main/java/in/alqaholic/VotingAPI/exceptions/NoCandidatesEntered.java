package in.alqaholic.VotingAPI.exceptions;

import org.springframework.http.HttpStatus;

public class NoCandidatesEntered extends ApiException {
    public NoCandidatesEntered() {
        super("No candidates entered!", HttpStatus.NOT_FOUND);
    }
}
