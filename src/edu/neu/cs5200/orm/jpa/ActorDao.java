package edu.neu.cs5200.orm.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ActorDao {
	private static final String UNIT = "JPAWeb";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public int createActor(Actor actor) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(actor);
		em.flush();
		em.getTransaction().commit();
		
		em.close();
		
		return actor.getId();
	}
	
	public static void main(String[] args) {
		
		ActorDao dao = new ActorDao();
		
		Actor actor = new Actor("Will", "Smith");
		actor.setOscarNominations(3);
		
		dao.createActor(actor);
	}

}
