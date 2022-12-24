package in.alqaholic.VoingAPI.services;

import org.springframework.stereotype.Service;

import in.alqaholic.VoingAPI.exceptions.CandidateAlreadyExists;
import in.alqaholic.VoingAPI.exceptions.NoCandidatesEntered;
import in.alqaholic.VoingAPI.repository.VoteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    public void enterCandidate(String name) {
        if (voteRepository.exists(name)) {
            throw new CandidateAlreadyExists();
        }

        voteRepository.enterCandidate(name);
    }

    public String getWinner() {
        String winner = voteRepository.getWinner();

        if (winner == null || winner.isBlank()) {
            throw new NoCandidatesEntered();
        } else {
            return "The winner is " + winner;
        }
    }

}
