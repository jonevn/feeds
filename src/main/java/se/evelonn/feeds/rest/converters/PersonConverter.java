package se.evelonn.feeds.rest.converters;

import java.net.URI;

import org.jboss.resteasy.plugins.providers.atom.Person;

import se.evelonn.feeds.domain.AtomPerson;

public class PersonConverter {

	public static Person convertToRepresentation(AtomPerson from) {
		try {
			Person to = new Person();
			to.setBase(new URI(from.getBase()));
			to.setEmail(from.getEmail());
			to.setLanguage(from.getLanguage());
			to.setName(from.getName());
			to.setUri(new URI(from.getUri()));
			return to;
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to convert person to representation: "
							+ e.getMessage(), e);
		}

	}

}
