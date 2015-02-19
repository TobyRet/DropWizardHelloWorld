package com.codurance.resources;

import com.codurance.views.GigFormView;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;


@Path("gigs")
public class GigResource {
	@GET
	@Path("/new")
	public GigFormView getAllGigs() {
		return new GigFormView();
	}

	@POST
	@Path("/create")
	public Response create(String gig) {
		URI redirection = URI.create("/success");
		return Response.seeOther(redirection).build();
	}

}
