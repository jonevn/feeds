package se.evelonn.feeds.rest.resources;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.atom.Entry;

import se.evelonn.feeds.rest.converters.EntryConverter;
import se.evelonn.feeds.service.FeedService;

@Path("/entry")
public class EntryResource {

	@Inject
	private FeedService feedService;

	@GET
	@Path("/{entryId}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Entry getEntry(@PathParam("entryId") URI entryId) {
		return EntryConverter.convertToRepresentation(feedService.find(
				se.evelonn.feeds.domain.AtomEntry.class,
				Integer.parseInt(entryId.getPath())));
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Entry saveEntry(Entry entry) {
		return EntryConverter.convertToRepresentation(feedService
				.merge(EntryConverter.convertToEntity(entry)));
	}
}
