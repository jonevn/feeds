package se.evelonn.feeds.service;

import java.sql.Timestamp;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import se.evelonn.feeds.dao.FeedDAO;
import se.evelonn.feeds.domain.AtomEntry;
import se.evelonn.feeds.domain.AtomFeed;
import se.evelonn.feeds.domain.AtomLink;
import se.evelonn.feeds.domain.Event;

@Stateless
@LocalBean
public class FeedService {

	private static final String GET_FEED_URL = "http://localhost:8080/feeds/feed/";

	private static final Logger logger = Logger.getLogger(FeedService.class);

	private static final int NUMBER_OF_ENTRYS_IN_FEED = 5;

	@Inject
	private FeedDAO feedDao;

	/**
	 * Method that will loop through all events that do not belong to a feed and
	 * connect them.
	 */
	@Schedule(minute = "*/2", hour = "*", persistent = false)
	public void addEventsToFeed() {
		logger.info("Performing scheduled work");
		List<Event> eventsWithoutFeed = feedDao.getEventsWithoutFeed();
		if (eventsWithoutFeed.isEmpty()) {
			logger.info("No events to handle");
			return;
		}
		logger.info(eventsWithoutFeed.size() + " event/s to add");
		Stack<AtomFeed> feedStack = new Stack<AtomFeed>();

		AtomFeed latestFeed = getLatestFeed();

		// Fill up the latest feed
		if (latestFeed != null) {
			logger.info("Latest feed exists");
			Iterator<Event> iterator = eventsWithoutFeed.iterator();

			while (iterator.hasNext()
					&& latestFeed.getEntries().size() < NUMBER_OF_ENTRYS_IN_FEED) {
				Event event = (Event) iterator.next();
				latestFeed.getEntries().add(
						createEntryForEvent(event, latestFeed));
				iterator.remove();
			}
			latestFeed.setUpdated(new Timestamp(System.currentTimeMillis()));
			feedStack.push(feedDao.merge(latestFeed));
		}

		// No more work to be done
		if (eventsWithoutFeed.isEmpty()) {
			logger.info("Finished performing scheduled work, not creating new feeds");
			return;
		}

		Iterator<Event> iterator = eventsWithoutFeed.iterator();
		while (iterator.hasNext()) {
			AtomFeed atomFeed = new AtomFeed();
			atomFeed.setUpdated(new Timestamp(System.currentTimeMillis()));
			for (int i = 0; i < NUMBER_OF_ENTRYS_IN_FEED; i++) {
				if (!iterator.hasNext()) {
					break;
				}
				Event event = (Event) iterator.next();
				atomFeed.getEntries().add(createEntryForEvent(event, atomFeed));
			}
			feedStack.push(feedDao.merge(atomFeed));
		}

		setupLinksForFeeds(feedStack);
		logger.info("Finished performing scheduled work");
	}

	private void setupLinksForFeeds(Stack<AtomFeed> feedStack) {

		while (!feedStack.isEmpty()) {
			AtomFeed current = feedStack.pop();
			try {
				AtomFeed next = feedStack.peek();

				// Create link to next feed
				AtomLink nextLink = new AtomLink();
				nextLink.setRel("next");
				nextLink.setHref(GET_FEED_URL + next.getFeedId());
				nextLink.setMediaType(MediaType.APPLICATION_ATOM_XML_TYPE
						.toString());
				current.getLinks().add(feedDao.merge(nextLink));

				// Create link to previous feed in the next feed
				AtomLink previousLink = new AtomLink();
				previousLink.setRel("previous");
				previousLink.setHref(GET_FEED_URL + current.getFeedId());
				previousLink.setMediaType(MediaType.APPLICATION_ATOM_XML_TYPE
						.toString());
				next.getLinks().add(feedDao.merge(previousLink));
			} catch (EmptyStackException e) {
				// Do nothing, no links will be added
			}

			feedDao.merge(current);
		}
	}

	private AtomEntry createEntryForEvent(Event event, AtomFeed feed) {
		AtomEntry atomEntry = new AtomEntry();
		atomEntry.setEvent(event);
		atomEntry.setPublished(event.getPublished());
		atomEntry.setFeed(feed);
		// TODO: Set more values

		event.setEntry(atomEntry);
		return atomEntry;
	}

	public AtomFeed getLatestFeed() {
		return feedDao.getLatestFeed();
	}

	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return feedDao.find(entityClass, primaryKey);
	}

	public <T> void delete(T entity) {
		feedDao.delete(entity);
	}

	public <T> List<T> findAll(Class<T> entityClass) {
		return feedDao.findAll(entityClass);
	}

	public <T> T merge(T entity) {
		return feedDao.merge(entity);
	}
}
