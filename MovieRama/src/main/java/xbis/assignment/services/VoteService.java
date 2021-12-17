package xbis.assignment.services;

import java.util.List;

import xbis.assignment.models.Vote;

public interface VoteService {
	
	public Vote getVote(long voteId);

	public List<Vote> getAllVotes();

	public Vote addVote(Vote vote);
	
	public void deleteVote(long voteID);
	
	public long getAllLikes(long movieId);
	
	public long getAllHates(long movieId);
	
}
