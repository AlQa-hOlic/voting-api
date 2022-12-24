package in.alqaholic.VoingAPI.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.alqaholic.VoingAPI.dto.VotingApiResponse;
import in.alqaholic.VoingAPI.services.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tags(value = { @Tag(name = "candidate") })
public class CandidateController {
    private final VoteService voteService;

    @PostMapping("/entercandidate")
    @Operation(summary = "Enter a candidate for voting")
    public ResponseEntity<VotingApiResponse> enterCandidate(@RequestParam("name") String name) {
        voteService.enterCandidate(name);
        return ResponseEntity.ok(new VotingApiResponse("success"));
    }

    @GetMapping("/getwinner")
    @Operation(summary = "Get the candidates with the highest number of votes")
    public ResponseEntity<VotingApiResponse> getWinner() {
        String result = voteService.getWinner();

        return ResponseEntity.ok(new VotingApiResponse(result));
    }

}
