package com.codurance.helloworld;

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



