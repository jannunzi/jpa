package edu.neu.cs5200.orm.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MovieDao {
	private static final String UNIT = "JPAWeb";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public void createMovie(Movie movie) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(movie);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public Movie findMovieById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	public void deleteMovie(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		em.remove(movie);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Movie> findAllMovies() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m");
		List<Movie> movies = (List<Movie>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return movies;
	}

	
	public Movie findMovieByTitle(String title) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m where m.title=:title");
		query.setParameter("title", title);
		Movie movie = (Movie)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	public void renameMovie(int id, String newTitle) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		movie.setTitle(newTitle);
		em.getTransaction().commit();
	}

	
	public static void main(String[] args) {
		MovieDao mdao = new MovieDao();
		
//		mdao.renameMovie(3, "Terminator 2");
		
		List<Movie> movies = mdao.findAllMovies();
		for(Movie m : movies) {
			System.out.println(m.getTitle());
		}
		
//		Movie movie = mdao.findMovieByTitle("Titanic");
//		System.out.println(movie.getTitle());

		
//		Movie titanic = new Movie("Titanic");
//		Movie movie = new Movie("Aliens");
//		mdao.createMovie(movie);
//		movie = new Movie("Avatar");
//		mdao.createMovie(movie);
		
//		Movie movie = mdao.findMovieById(2);
//		System.out.println(movie.getTitle());
		
//		mdao.deleteMovie(2);
	}

}
