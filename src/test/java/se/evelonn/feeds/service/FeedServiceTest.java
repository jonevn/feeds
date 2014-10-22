package se.evelonn.feeds.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import se.evelonn.feeds.dao.FeedDAO;
import se.evelonn.feeds.domain.AtomFeed;
import se.evelonn.feeds.domain.Event;

public class FeedServiceTest {

	@Mock
	private FeedDAO feedDao;

	@InjectMocks
	private FeedService feedService;

	@Captor
	private ArgumentCaptor<AtomFeed> feedCaptor;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddEventsToFeedWhenNoFeedExistCreatingNewWithFiveEntrys() {
		given(feedDao.getEventsWithoutFeed()).willReturn(
				createEntryListContainingFiveElements());
		given(feedDao.getLatestFeed()).willReturn(null);

		feedService.addEventsToFeed();

		verify(feedDao, times(2)).merge(feedCaptor.capture());

		AtomFeed feed = feedCaptor.getAllValues().get(0);
		assertTrue(feed.getEntries().size() == 5);
	}

	private List<Event> createEntryListContainingFiveElements() {
		List<Event> entries = new ArrayList<Event>();
		for (int i = 0; i < 5; i++) {
			Event admissionEvent = new Event();
			admissionEvent.setPublished(new Timestamp(System
					.currentTimeMillis() - (i * 1000)));
			admissionEvent.setPersonId("person" + i);
			entries.add(admissionEvent);
		}
		return entries;
	}
}
