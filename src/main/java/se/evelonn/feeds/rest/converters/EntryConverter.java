package se.evelonn.feeds.rest.converters;

import java.net.URI;
import java.util.Date;

import org.jboss.resteasy.plugins.providers.atom.Content;
import org.jboss.resteasy.plugins.providers.atom.Entry;

import se.evelonn.feeds.domain.AtomCategory;
import se.evelonn.feeds.domain.AtomEntry;
import se.evelonn.feeds.domain.AtomLink;
import se.evelonn.feeds.domain.AtomPerson;

public class EntryConverter {

	public static Entry convertToRepresentation(AtomEntry from) {
		try {
			Entry to = new Entry();
			if (from.getBase() != null) {
				to.setBase(new URI(from.getBase()));
			}
			to.setId(new URI("http://feeds.evelonn.se/" + from.getId()));
			to.setLanguage(from.getLanguage());
			if (from.getPublished() != null) {
				to.setPublished(new Date(from.getPublished().getTime()));
			}
			to.setRights(from.getRights());
			to.setSource(SourceConverter.convertToRepresentation(from.getFeed()));
			to.setSummary(from.getSummary());
			to.setTitle(from.getTitle());
			if (from.getUpdated() != null) {
				to.setUpdated(new Date(from.getUpdated().getTime()));
			}

			for (AtomPerson fromAuthor : from.getAuthors()) {
				to.getAuthors().add(
						PersonConverter.convertToRepresentation(fromAuthor));
			}

			for (AtomCategory fromCategory : from.getCategories()) {
				to.getCategories()
						.add(CategoryConverter
								.convertToRepresentation(fromCategory));
			}

			for (AtomPerson fromContributor : from.getContributors()) {
				to.getContributors().add(
						PersonConverter
								.convertToRepresentation(fromContributor));
			}

			for (AtomLink fromLink : from.getLinks()) {
				to.getLinks().add(
						LinkConverter.convertToRepresentation(fromLink));
			}

			Content content = new Content();
			content.setJAXBObject(EventConverter.convertToRepresentation(from
					.getEvent()));
			to.setContent(content);
			return to;
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to convert to Entry representation: "
							+ e.getMessage(), e);
		}
	}

	public static se.evelonn.feeds.domain.AtomEntry convertToEntity(Entry from) {
		AtomEntry to = new AtomEntry();

		return to;
	}
}
