package se.evelonn.feeds.service;

import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import se.evelonn.feeds.dao.FeedDAO;
import se.evelonn.feeds.domain.Event;

@Singleton
@Startup
public class DBPopulator {

	@Inject
	private FeedDAO feedDao;

	@PostConstruct
	public void populateDB() {
		Event event = new Event();

		event.setPersonId("person1");
		event.setPublished(new Timestamp(System.currentTimeMillis() - 50000));

		feedDao.merge(event);

		event = new Event();

		event.setPersonId("person2");
		event.setPublished(new Timestamp(System.currentTimeMillis() - 40000));

		feedDao.merge(event);

		event = new Event();

		event.setPersonId("person3");
		event.setPublished(new Timestamp(System.currentTimeMillis() - 30000));

		feedDao.merge(event);

		event = new Event();

		event.setPersonId("person4");
		event.setPublished(new Timestamp(System.currentTimeMillis() - 20000));

		feedDao.merge(event);

		event = new Event();

		event.setPersonId("person5");
		event.setPublished(new Timestamp(System.currentTimeMillis() - 10000));

		feedDao.merge(event);

		event = new Event();
		event.setPersonId("person6");

		event.setPublished(new Timestamp(System.currentTimeMillis() + 10000));

		feedDao.merge(event);

	}
}
