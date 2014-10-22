package se.evelonn.feeds.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AtomFeedTest extends DomainTest {

	@Test
	public void testMergeAtomFeed() {
		AtomFeed feed = (AtomFeed) session.merge(new AtomFeed());

		assertNotNull(session.get(AtomFeed.class, feed.getFeedId()));
	}

	@Test
	public void testMergeAtomFeedWithAtomEntries() {

		AtomFeed atomFeed = new AtomFeed();
		atomFeed.getEntries().add(new AtomEntry());
		atomFeed.getEntries().add(new AtomEntry());

		atomFeed = (AtomFeed) session.merge(atomFeed);

		assertEquals(
				2,
				((AtomFeed) session.byId(AtomFeed.class).load(
						atomFeed.getFeedId())).getEntries().size());

		assertEquals(2, session.createQuery("select ae from AtomEntry ae")
				.list().size());
	}

	@Test
	public void testMergeAtomFeedWithAtomEntryContainingEvent() {

		AtomFeed atomFeed = new AtomFeed();
		AtomEntry atomEntry = new AtomEntry();
		atomEntry.setEvent(new Event());
		atomFeed.getEntries().add(atomEntry);

		atomFeed = (AtomFeed) session.merge(atomFeed);

		assertEquals(
				1,
				((AtomFeed) session.byId(AtomFeed.class).load(
						atomFeed.getFeedId())).getEntries().size());

		assertEquals(1, session.createQuery("select ae from AtomEntry ae")
				.list().size());

		assertEquals(1, session.createQuery("select e from Event e").list()
				.size());
	}
}
