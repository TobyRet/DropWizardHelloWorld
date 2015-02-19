package com.codurance.resources;

import com.codurance.views.HelloWorldView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/greeting/{name}")
public class HelloWorldResource {
	@GET
	public HelloWorldView getGreeting(@PathParam("name") String name) {
		return new HelloWorldView(name);
	}
}



