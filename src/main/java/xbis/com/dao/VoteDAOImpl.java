package xbis.com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xbis.com.models.Vote;

@Repository
public class VoteDAOImpl implements VoteDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFac) {
		this.sessionFactory = sessionFac;
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}
	
	@Override
	public List<Vote> getAllVotes(){
		Session session = this.sessionFactory.getCurrentSession();
		List<Vote> voteList = session.createQuery("from Vote").list();
		return voteList;
	}
	
	@Override
	public Vote getVote(long voteId) {
		Session session = this.sessionFactory.getCurrentSession();
		Vote vote = (Vote) session.get(Vote.class, voteId);
		return vote;
	}
	
	@Override
	public Vote addVote(Vote vote) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(vote);
		return vote;
	}
	
	@Override
	public void deleteVote(long voteID) {
		Session session = this.sessionFactory.getCurrentSession();
		Vote vote = (Vote) session.load(Vote.class, voteID);
		if(vote != null) {
			session.delete(vote);
		}
	}
	
	@Override
	public long getAllLikes(long movieId) {
		Session session = sessionFactory.openSession();
		String mySQLquery = "from Vote as v where v.movie.movieId=?0 and v.positive=true";
		Query query = session.createQuery(mySQLquery);
		query.setParameter(0, movieId);
		List list = query.getResultList();
		return list.size();
	}
	
	@Override
	public long getAllHates(long movieId) {
		Session session = sessionFactory.openSession();
		String mySQLquery = "from Vote as v where v.movie.movieId=?0 and v.positive=false";
		Query query = session.createQuery(mySQLquery);
		query.setParameter(0, movieId);
		List list = query.getResultList();
		return list.size();
	}
}
