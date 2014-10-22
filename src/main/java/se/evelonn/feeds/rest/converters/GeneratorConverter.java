package se.evelonn.feeds.rest.converters;

import java.net.URI;

import org.jboss.resteasy.plugins.providers.atom.Generator;

import se.evelonn.feeds.domain.AtomGenerator;

public class GeneratorConverter {

	public static Generator convertToRepresentation(AtomGenerator from) {
		try {
			Generator to = new Generator();
			to.setBase(new URI(from.getBase()));
			to.setLanguage(from.getLanguage());
			to.setText(from.getText());
			to.setUri(new URI(from.getUri()));
			to.setVersion(from.getVersion());
			return to;
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to convert to Generator representation: "
							+ e.getMessage(), e);
		}
	}
}