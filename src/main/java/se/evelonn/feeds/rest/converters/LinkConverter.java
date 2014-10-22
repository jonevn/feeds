package se.evelonn.feeds.rest.converters;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.atom.Link;

import se.evelonn.feeds.domain.AtomLink;

public class LinkConverter {

	public static Link convertToRepresentation(AtomLink from) {
		try {
			Link to = new Link();
			if (from.getBase() != null) {
				to.setBase(new URI(from.getBase()));
			}
			if (from.getHref() != null) {
				to.setHref(new URI(from.getHref()));
			}
			to.setHreflang(from.getHreflang());
			to.setLanguage(from.getLanguage());
			to.setLength(from.getLength());
			to.setRel(from.getRel());
			to.setTitle(from.getTitle());
			if (from.getMediaType() != null) {
				to.setType(MediaType.valueOf(from.getMediaType()));
			}
			return to;
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to convert to Link representation: "
							+ e.getMessage(), e);
		}
	}

}
