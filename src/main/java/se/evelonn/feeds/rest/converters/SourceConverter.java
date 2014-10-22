package se.evelonn.feeds.rest.converters;

import java.net.URI;
import java.util.Date;

import org.jboss.resteasy.plugins.providers.atom.Source;

import se.evelonn.feeds.domain.AtomCategory;
import se.evelonn.feeds.domain.AtomFeed;
import se.evelonn.feeds.domain.AtomLink;
import se.evelonn.feeds.domain.AtomPerson;

public class SourceConverter {

	public static Source convertToRepresentation(AtomFeed from) {
		try {
			Source to = new Source();
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
			to.setId(new URI("http://feeds.evelonn.se/" + from.getFeedId()));
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

			for (AtomLink fromLink : from.getLinks()) {
				to.getLinks().add(
						LinkConverter.convertToRepresentation(fromLink));
			}

			return to;
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert to Source: "
					+ e.getMessage(), e);
		}

	}

}
