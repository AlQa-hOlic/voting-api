package in.alqaholic.VotingAPI.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import in.alqaholic.VotingAPI.exceptions.CandidateAlreadyExists;
import in.alqaholic.VotingAPI.exceptions.CandidateDoesNotExist;
import in.alqaholic.VotingAPI.exceptions.NoCandidatesEntered;
import in.alqaholic.VotingAPI.repository.VoteRepository;
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

    public Map<String, Integer> getVoteList() {
        return voteRepository.getVoteList();
    }

    public int getVotes(String name) {
        if (!voteRepository.exists(name)) {
            throw new CandidateDoesNotExist();
        }

        return voteRepository.getVotes(name);
    }

    public void castVote(String name) {
        voteRepository.castVote(name);
    }

}
