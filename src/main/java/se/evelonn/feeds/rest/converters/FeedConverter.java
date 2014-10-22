package se.evelonn.feeds.rest.converters;

import java.net.URI;
import java.util.Date;

import org.jboss.resteasy.plugins.providers.atom.Feed;

import se.evelonn.feeds.domain.AtomCategory;
import se.evelonn.feeds.domain.AtomEntry;
import se.evelonn.feeds.domain.AtomFeed;
import se.evelonn.feeds.domain.AtomLink;
import se.evelonn.feeds.domain.AtomPerson;

public class FeedConverter {

	public static Feed convertToRepresentation(AtomFeed from) {
		try {
			Feed to = new Feed();
			to.setId(new URI("http://feeds.evelonn.se/" + from.getFeedId()));
			if (from.getBase() != null) {
				to.setBase(new URI(from.getBase()));
			}
			if (from.getGenerator() != null) {
				to.setGenerator(GeneratorConverter.convertToRepresentation(from
						.getGenerator()));
			}
			if (from.getIcon() != null) {
				to.setIcon(new URI(from.getIcon()));
			}
			to.setLanguage(from.getLanguage());
			if (from.getLogo() != null) {
				to.setLogo(new URI(from.getLogo()));
			}
			to.setRights(from.getRights());
			to.setSubtitle(from.getSubtitle());
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

			for (AtomEntry fromEntry : from.getEntries()) {
				to.getEntries().add(
						EntryConverter.convertToRepresentation(fromEntry));
			}

			for (AtomLink fromLink : from.getLinks()) {
				to.getLinks().add(
						LinkConverter.convertToRepresentation(fromLink));
			}

			return to;
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to convert to Feed representation: "
							+ e.getMessage(), e);
		}

	}

	public static AtomFeed convertToEntity(Feed from) {
		AtomFeed to = new AtomFeed();
		return to;
	}
}
