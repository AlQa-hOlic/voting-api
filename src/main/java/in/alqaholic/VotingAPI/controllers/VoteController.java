package in.alqaholic.VotingAPI.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.alqaholic.VotingAPI.dto.VotingApiResponse;
import in.alqaholic.VotingAPI.services.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tags(value = { @Tag(name = "vote") })
public class VoteController {
    private final VoteService voteService;

    @GetMapping("/listvote")
    @Operation(summary = "List all candidates' votes")
    public ResponseEntity<Map<String, Integer>> listVote() {
        return ResponseEntity.ok(voteService.getVoteList());
    }

    @GetMapping("/countvote")
    @Operation(summary = "Get votes for a given candidate")
    public ResponseEntity<VotingApiResponse> countVote(@RequestParam("name") String name) {
        return ResponseEntity
                .ok(new VotingApiResponse("The number of votes for " + name + " is " + voteService.getVotes(name)));
    }

    @PostMapping("/castvote")
    @Operation(summary = "Cast a vote for a given candidate")
    public ResponseEntity<VotingApiResponse> castVote(@RequestParam("name") String name) {
        voteService.castVote(name);
        return ResponseEntity.ok(new VotingApiResponse("success"));
    }
}
