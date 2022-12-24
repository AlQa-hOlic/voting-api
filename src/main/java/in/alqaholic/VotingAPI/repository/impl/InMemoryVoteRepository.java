package in.alqaholic.VotingAPI.repository.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import in.alqaholic.VotingAPI.repository.VoteRepository;

@Repository
public class InMemoryVoteRepository implements VoteRepository {
    private final HashMap<String, Integer> voteList = new HashMap<>();

    @Override
    public boolean exists(String name) {
        return voteList.containsKey(name);
    }

    @Override
    public void enterCandidate(String name) {
        voteList.putIfAbsent(name, 0);
    }

    @Override
    public String getWinner() {
        return voteList.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
