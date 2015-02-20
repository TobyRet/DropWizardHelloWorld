package com.codurance.views;

import io.dropwizard.views.View;

import java.util.List;

public class EventsView extends View {
	private final List<String> list;

	public EventsView(List<String> list) {
		super("all-events.mustache");
		this.list = list;
	}

	public List<String> getList() {
		return list;
	}
}
