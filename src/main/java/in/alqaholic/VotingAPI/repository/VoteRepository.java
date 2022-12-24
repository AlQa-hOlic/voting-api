package in.alqaholic.VotingAPI.repository;

public interface VoteRepository {
    /**
     * Checks whether candidate exists in the votes data.
     * 
     * @param name
     * @return if candidate is present or not
     */
    boolean exists(String name);

    /**
     * Insert new candidate into the votes data. Candidate ignored if already
     * present!
     * 
     * @param name
     */
    void enterCandidate(String name);

    /**
     * Fetch the candidate with the most counts. Returns null if there are no
     * candidates.
     * 
     */
    String getWinner();
}
