package in.alqaholic.VotingAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import in.alqaholic.VotingAPI.exceptions.CandidateAlreadyExists;
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
}
