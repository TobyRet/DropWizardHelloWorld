package com.codurance.resources;

import com.codurance.model.Event;
import com.codurance.model.EventsRepo;
import com.codurance.views.EventFormView;
import com.codurance.views.EventView;

import javax.ws.rs.*;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.ws.rs.core.Response.seeOther;


@Path("/event")
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
	@Path("{eventName}")
	@Produces("text/html")
	public EventView getEvent(@PathParam("eventName") String eventName) {
		return new EventView(eventsRepo.fetchEvent(eventName));
	}

	@POST
	public ResponseBuilder create(@FormParam("gigListingName") String name,
                                   @FormParam("gigListingArtist") String artist,
                                   @FormParam("gigListingDate") String dateText,
                                   @FormParam("gigListingGenre") String genre,
                                   @FormParam("gigListingLocation") String location) throws IOException {

		LocalDate date = LocalDate.parse(dateText, DATE_CONVERSION);

		Event event = new Event(name, artist, date, genre, location);
		eventsRepo.add(event);
		return seeOther(URI.create("/"));
	}

}
