package com.codurance.helloworld;

import io.dropwizard.views.View;

public class HelloWorldView extends View {
	private final String name;

	public HelloWorldView(String name) {
		super("hello-world.ftl");
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
