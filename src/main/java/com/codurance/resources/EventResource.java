package com.codurance.resources;

import com.codurance.model.Event;
import com.codurance.model.EventsRepo;
import com.codurance.views.EventFormView;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value="event", description="Operations about events")
@Path("/event")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class EventResource {
	public static final DateTimeFormatter DATE_CONVERSION = DateTimeFormatter.ISO_LOCAL_DATE;
	private final EventsRepo eventsRepo;

	public EventResource(EventsRepo eventsRepo) {
		this.eventsRepo = eventsRepo;
	}

	@GET
	@Path("/new")
	public EventFormView getNewEventForm() {
		return new EventFormView();
	}

	@GET
	@Path("/{eventId}")
	public String getEvent(@PathParam("eventId") String eventId) {
		return eventsRepo.fetchEvent(Integer.parseInt(eventId)).getArtist();
	}

	@POST
	@ApiOperation(value = "Create a new event")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid input") })
	public ResponseBuilder create(@FormParam("gigListingName") String name,
                                   @FormParam("gigListingArtist") String artist,
                                   @FormParam("gigListingDate") String dateText,
                                   @FormParam("gigListingGenre") String genre,
                                   @FormParam("gigListingLocation") String location) throws IOException {

		LocalDate date = LocalDate.parse(dateText, DATE_CONVERSION);

		Event event = new Event(name, artist, date, genre, location);

		eventsRepo.add(event);
		return Response.seeOther(URI.create("/"));
	}

}
