package se.evelonn.feeds.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

import se.evelonn.feeds.rest.converters.EventConverter;
import se.evelonn.feeds.rest.representations.EventRepresentation;
import se.evelonn.feeds.service.FeedService;

@Path("/event")
public class EventResource {

	@Inject
	private FeedService feedService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEvent(EventRepresentation event) {
		feedService.merge(EventConverter.convertToEntity(event));
		return new ResponseBuilderImpl().status(Status.OK).build();
	}
}
