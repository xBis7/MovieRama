package xbis.com.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xbis.com.models.Movie;

@Repository
public class MovieDAOImpl implements MovieDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFac) {
		this.sessionFactory = sessionFac;
	}
	
	@Override
	public List<Movie> getAllMovies(){
		Session session = this.sessionFactory.getCurrentSession();
		List<Movie> movieList = session.createQuery("from Movie").list();
		return movieList;
	}

	@Override
	public List<Movie> getUserMovies(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		//Movie movie = (Movie) session.get(Movie.class, id);
		String mySQLquery = "from Movie as m where m.user.userId=?0";
		Query query = session.createQuery(mySQLquery);
		query.setParameter(0, id);
		List<Movie> list = query.getResultList();
		return list;
	}
	
	@Override
	public Movie getMovie(long movieID) {
		Session session = this.sessionFactory.getCurrentSession();
		Movie movie = (Movie) session.get(Movie.class, movieID);
		return movie;
	}
	
	@Override
	public Movie addMovie(Movie movie) {
		Session session = this.sessionFactory.getCurrentSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		movie.setDate(dtf.format(now));
		session.persist(movie);
		return movie;
	}
	
	@Override
	public void updateMovieLikes(Movie movie, long likes) {
		Session session = this.sessionFactory.getCurrentSession();
		movie.setLikes(likes);
		session.update(movie);
	}
	
	@Override
	public void updateMovieHates(Movie movie, long hates) {
		Session session = this.sessionFactory.getCurrentSession();
		movie.setHates(hates);
		session.update(movie);
	}
	
	@Override
	public List<Movie> sortByLikes() {
		Session session = this.sessionFactory.getCurrentSession();
		String mySQLquery = "from Movie order by likes desc";
		Query query = session.createQuery(mySQLquery);
		
		List<Movie> list = query.getResultList();
		return list;
	}
	
	@Override
	public List<Movie> sortByHates() {
		Session session = this.sessionFactory.getCurrentSession();
		String mySQLquery = "from Movie order by hates desc";
		Query query = session.createQuery(mySQLquery);
		
		List<Movie> list = query.getResultList();
		return list;
	}
	
	@Override
	public List<Movie> sortByDate() {
		Session session = this.sessionFactory.getCurrentSession();
		String mySQLquery = "from Movie order by date desc";
		Query query = session.createQuery(mySQLquery);
		
		List<Movie> list = query.getResultList();
		return list;
	}
	
}
