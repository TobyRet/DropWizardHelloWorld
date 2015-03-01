package com.codurance.resources;

import com.codurance.model.Event;
import com.codurance.model.EventsRepo;
import com.codurance.views.EventFormView;
import com.codurance.views.EventsView;
import com.google.common.collect.ImmutableList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Path("/events")
public class EventsResource {
	public static final DateTimeFormatter DATE_CONVERSION = DateTimeFormatter.ISO_LOCAL_DATE;
	private final EventsRepo events;

	public EventsResource(EventsRepo events) {
		this.events = events;
	}

	@GET
	@Path("/new")
	public EventFormView getNewEventForm() {
		return new EventFormView();
	}

	@GET
	@Path("/all")
	public EventsView getAllEvents() {
		List<String> list = ImmutableList.of("red", "green", "blue");
		return new EventsView(list);

	}

	@POST
	@Path("/create")
	public void create( @FormParam("gigListingName") String name,
						@FormParam("gigListingArtist") String artist,
						@FormParam("gigListingDate") String dateText,
						@FormParam("gigListingGenre") String genre,
						@FormParam("gigListingLocation") String location,
						@Context HttpServletResponse servletResponse) throws IOException {

		LocalDate date = LocalDate.parse(dateText, DATE_CONVERSION);

		Event event = new Event(name, artist, date, genre, location);
		events.add(event);
		servletResponse.sendRedirect("/events/all");
	}

}
