package in.alqaholic.VoingAPI;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import in.alqaholic.VoingAPI.exceptions.CandidateAlreadyExists;
import in.alqaholic.VoingAPI.repository.VoteRepository;
import in.alqaholic.VoingAPI.services.VoteService;

public class VotingServiceTests {

    @Test
    void addCandidate() {
        String name = "test";

        VoteRepository voteRepository = Mockito.mock(VoteRepository.class);
        VoteService voteService = new VoteService(voteRepository);

        when(voteRepository.exists(name)).thenReturn(false);
        voteService.enterCandidate(name);
        verify(voteRepository).enterCandidate(name);
    }

    @Test
    void doNotAddDuplicateCandidate() {
        String name = "test";

        VoteRepository voteRepository = Mockito.mock(VoteRepository.class);
        VoteService voteService = new VoteService(voteRepository);

        when(voteRepository.exists(name)).thenReturn(true);
        assertThrows(CandidateAlreadyExists.class, () -> voteService.enterCandidate(name),
                "Candidate already exists");
        verify(voteRepository, never()).enterCandidate(name);
    }
}
