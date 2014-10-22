package se.evelonn.feeds.rest.converters;

import java.net.URI;

import org.jboss.resteasy.plugins.providers.atom.Category;

import se.evelonn.feeds.domain.AtomCategory;

public class CategoryConverter {

	public static Category convertToRepresentation(AtomCategory from) {
		try {
			Category to = new Category();
			to.setBase(new URI(from.getBase()));
			to.setLabel(from.getLabel());
			to.setLanguage(from.getLanguage());
			to.setScheme(new URI(from.getScheme()));
			to.setTerm(from.getTerm());
			return to;
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to convert to Category representation: "
							+ e.getMessage(), e);
		}

	}
}
