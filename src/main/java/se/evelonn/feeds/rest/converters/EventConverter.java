package se.evelonn.feeds.rest.converters;

import java.sql.Timestamp;

import se.evelonn.feeds.domain.Event;
import se.evelonn.feeds.rest.representations.EventRepresentation;

public class EventConverter {

	public static EventRepresentation convertToRepresentation(Event from) {
		EventRepresentation to = new EventRepresentation();
		to.setPersonId(from.getPersonId());
		return to;
	}

	public static Event convertToEntity(EventRepresentation from) {
		Event to = new Event();
		to.setPersonId(from.getPersonId());
		to.setPublished(Timestamp.valueOf(from.getPublished()));
		return to;
	}
}
