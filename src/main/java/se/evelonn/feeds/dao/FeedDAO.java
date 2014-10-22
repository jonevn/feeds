package se.evelonn.feeds.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import se.evelonn.feeds.domain.AtomFeed;
import se.evelonn.feeds.domain.Event;

public class FeedDAO {

	@PersistenceContext(unitName = "se.evelonn.feeds")
	private EntityManager em;

	public List<Event> getEventsWithoutFeed() {
		return em
				.createQuery(
						"select e from Event e where e.entry = null order by published",
						Event.class).getResultList();
	}

	public AtomFeed getLatestFeed() {
		try {
			return em
					.createQuery(
							"select f from AtomFeed f order by updated desc",
							AtomFeed.class).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public <T> T merge(T objectToPersist) {
		return em.merge(objectToPersist);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return em.find(entityClass, primaryKey);
	}

	public <T> List<T> findAll(Class<T> entityClass) {
		return em.createQuery(
				"select o from " + entityClass.getSimpleName() + " o",
				entityClass).getResultList();
	}

	public <T> void delete(T entity) {
		em.remove(entity);
	}
}
