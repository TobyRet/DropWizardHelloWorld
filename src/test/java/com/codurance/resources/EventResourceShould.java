package com.codurance.resources;

import com.codurance.model.Event;
import com.codurance.model.EventsRepo;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventResourceShould {

	private static final EventsRepo eventsRepo = mock(EventsRepo.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new EventResource(eventsRepo))
			.build();
	private static final String EVENT_NAME = "Some name";
	private static final String EVENT_ARTIST = "Some artist";
	private static final String EVENT_GENRE = "Some genre";
	private static final String LOCATION = "Some location";
	private static final String EVENT_DATE = "2006-12-12";
	private Event event;

	@Before
	public void setup() {
		LocalDate date = LocalDate.parse(EVENT_DATE, DateTimeFormatter.ISO_LOCAL_DATE);
		event = new Event(EVENT_NAME, EVENT_ARTIST, date, EVENT_GENRE, LOCATION);
		given(eventsRepo.fetchEvent(eq("someEvent"))).willReturn(event);
		reset(eventsRepo);
	}

	@Test
	public void get_event() {
		assertThat(resources.client().resource("/event/someEvent").get(Event.class))
				.isEqualTo(event);
		verify(eventsRepo).fetchEvent("someEvent");
	}

}