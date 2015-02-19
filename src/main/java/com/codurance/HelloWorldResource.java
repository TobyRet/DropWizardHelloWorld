package com.codurance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/greeting/{name}")
public class HelloWorldResource {
	@GET
	public String getGreeting(@PathParam("name") String name) {
		return "Hello " + name + "!";
	}
}
