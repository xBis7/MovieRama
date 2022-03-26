package xbis.com.dao;

import java.util.List;

import xbis.com.models.Vote;

public interface VoteDAO {

	public List<Vote> getAllVotes();
	
	public Vote getVote(long voteId);
	
	public Vote addVote(Vote vote);
	
	public void deleteVote(long voteID);
	
	public long getAllLikes(long movieId);
	
	public long getAllHates(long movieId);
	
}
