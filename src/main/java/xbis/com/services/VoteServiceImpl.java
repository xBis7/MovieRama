package xbis.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xbis.com.dao.VoteDAO;
import xbis.com.models.Vote;

@Service("voteService")
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	VoteDAO voteDAO;
	
	@Transactional
	public Vote getVote(long voteId) {
		return voteDAO.getVote(voteId);
	}
	
	@Transactional
	public List<Vote> getAllVotes(){
		return voteDAO.getAllVotes();
	}
	
	@Transactional
	public Vote addVote(Vote vote) {
		return voteDAO.addVote(vote);
	}
	
	@Transactional
	public void deleteVote(long voteID) {
		voteDAO.deleteVote(voteID);
	}
	
	@Transactional
	public long getAllLikes(long movieId) {
		return voteDAO.getAllLikes(movieId);
	}
	
	@Transactional
	public long getAllHates(long movieId) {
		return voteDAO.getAllHates(movieId);
	}
	
}
