package com.codurance.views;

import com.codurance.model.Event;
import io.dropwizard.views.View;

public class EventView extends View {
	private final Event event;

	public EventView(Event event) {
		super("event.mustache");
		this.event = event;
	}
}
