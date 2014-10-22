package se.evelonn.feeds.rest.resources;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.atom.Feed;

import se.evelonn.feeds.rest.converters.FeedConverter;
import se.evelonn.feeds.service.FeedService;

@Path("/feed")
public class FeedResource {

	@Inject
	private FeedService feedService;

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Path("/latest")
	public Feed getLatestFeed() {
		return FeedConverter.convertToRepresentation(feedService
				.getLatestFeed());
	}

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Path("/{feedId}")
	public Feed getFeedById(@PathParam("feedId") URI feedId) {
		return FeedConverter.convertToRepresentation(feedService.find(
				se.evelonn.feeds.domain.AtomFeed.class,
				Integer.parseInt(feedId.getPath())));
	}

	@DELETE
	@Path("/{feedId}")
	public void deleteFeedWithId(@PathParam("feedId") URI feedId) {
		feedService.delete(feedService.find(
				se.evelonn.feeds.domain.AtomFeed.class,
				Integer.parseInt(feedId.getPath())));
	}
}
