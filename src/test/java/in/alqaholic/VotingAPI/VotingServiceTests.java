package in.alqaholic.VotingAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import in.alqaholic.VotingAPI.exceptions.CandidateAlreadyExists;
import in.alqaholic.VotingAPI.exceptions.CandidateDoesNotExist;
import in.alqaholic.VotingAPI.exceptions.NoCandidatesEntered;
import in.alqaholic.VotingAPI.repository.VoteRepository;
import in.alqaholic.VotingAPI.services.VoteService;

public class VotingServiceTests {

    private VoteRepository voteRepository = Mockito.mock(VoteRepository.class);
    private VoteService voteService = new VoteService(voteRepository);

    @BeforeEach
    void setup() {
        voteRepository = Mockito.mock(VoteRepository.class);
        voteService = new VoteService(voteRepository);
    }

    @Test
    void addCandidate() {
        String name = "test";

        when(voteRepository.exists(name)).thenReturn(false);
        voteService.enterCandidate(name);
        verify(voteRepository).enterCandidate(name);
    }

    @Test
    void doNotAddDuplicateCandidate() {
        String name = "test";

        when(voteRepository.exists(name)).thenReturn(true);
        assertThrows(CandidateAlreadyExists.class, () -> voteService.enterCandidate(name));
        verify(voteRepository, never()).enterCandidate(name);
    }

    @Test
    void getWinner() {
        String winnerName = "test";
        when(voteRepository.getWinner()).thenReturn(winnerName);
        assertEquals(voteService.getWinner(), "The winner is " + winnerName);
    }

    @Test
    void noWinner() {
        when(voteRepository.getWinner()).thenReturn(null);
        assertThrows(NoCandidatesEntered.class, () -> voteService.getWinner());
    }

    @Test
    void listVotes() {
        var voteList = Collections.singletonMap("test", 1);
        when(voteRepository.getVoteList()).thenReturn(voteList);
        assertTrue(() -> voteService.getVoteList().entrySet().stream()
                .allMatch(entry -> entry.getValue().equals(voteList.get(entry.getKey()))));
    }

    @Test
    void getVotes() {
        when(voteRepository.getVotes("test1")).thenReturn(2);
        when(voteRepository.exists("test1")).thenReturn(true);
        when(voteRepository.getVotes("test2")).thenReturn(4);
        when(voteRepository.exists("test2")).thenReturn(true);
        assertEquals(voteService.getVotes("test1"), 2);
        assertEquals(voteService.getVotes("test2"), 4);
    }

    @Test
    void getVotesForNonExistantCandidate() {
        when(voteRepository.exists("test1")).thenReturn(false);
        when(voteRepository.getVotes("test2")).thenReturn(4);
        when(voteRepository.exists("test2")).thenReturn(true);
        assertThrows(CandidateDoesNotExist.class, () -> voteService.getVotes("test1"));
        assertEquals(voteService.getVotes("test2"), 4);
    }

    @Test
    void castVote() {
        String name = "test";
        voteService.castVote(name);
        voteService.castVote(name);
        voteService.castVote(name);
        verify(voteRepository, times(3)).castVote(name);
    }

}
