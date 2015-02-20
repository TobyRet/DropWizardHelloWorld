package com.codurance.resources;

import com.codurance.db.CassandraClient;
import com.codurance.model.Event;
import com.codurance.views.GigFormView;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Path("gigs")
public class GigResource {
	public static final DateTimeFormatter DATE_CONVERSION = DateTimeFormatter.ISO_LOCAL_DATE;
	private final CassandraClient cassandraClient;

	public GigResource(CassandraClient cassandraClient) {
		this.cassandraClient = cassandraClient;
	}

	@GET
	@Path("/new")
	public GigFormView getAllGigs() {
		return new GigFormView();
	}

	@GET
	@Path("success")
	public String eventAddedSuccessfully() {
		return "Event added successfully";
	}

	@POST
	@Path("/create")
	public void create( @FormParam("gigListingName") String name,
						@FormParam("gigListingArtist") String artist,
						@FormParam("gigListingDate") String dateText,
						@FormParam("gigListingLocation") String location,
						@Context HttpServletResponse servletResponse) throws IOException {

		LocalDate date = LocalDate.parse(dateText, DATE_CONVERSION);

		Event event = new Event(name, artist, date, location);
		cassandraClient.add(event);
		servletResponse.sendRedirect("/success");
	}

}
