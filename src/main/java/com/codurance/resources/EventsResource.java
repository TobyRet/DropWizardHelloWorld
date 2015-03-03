package com.codurance.resources;

import com.codurance.model.EventsRepo;
import com.codurance.views.EventsView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/events")
public class EventsResource {
	private final EventsRepo eventsRepo;

	public EventsResource(EventsRepo eventsRepo) {
		this.eventsRepo = eventsRepo;
	}

	@GET
	public EventsView getAllEvents() {
		return new EventsView();
	}
}
