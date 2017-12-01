package edu.neu.cs5200.orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MovieLibraryDao {
	private static final String UNIT = "JPAWeb";
	private static EntityManagerFactory factory =
		Persistence.createEntityManagerFactory(UNIT);

	
	public void createMovieLibrary(MovieLibrary library) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(library);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void addMovieToLibrary(int mId, int lId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, mId);
		MovieLibrary library = em.find(MovieLibrary.class, lId);
		library.getMovies().add(movie);
		movie.setLibrary(library);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
//		MovieLibrary sciFi = new MovieLibrary("Sci Fi");
		MovieLibraryDao dao = new MovieLibraryDao();
//		dao.createMovieLibrary(sciFi);
		
		dao.addMovieToLibrary(3, 1);
		dao.addMovieToLibrary(4, 1);
		
	}

}
