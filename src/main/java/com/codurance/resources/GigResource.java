package com.codurance.resources;

import com.codurance.views.GigFormView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("gigs/new")
public class GigResource {
	@GET
	public GigFormView getAllGigs() {
		return new GigFormView();
	}
}
