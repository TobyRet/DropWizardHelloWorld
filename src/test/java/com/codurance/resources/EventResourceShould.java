package com.codurance.resources;

import com.codurance.model.Event;
import com.codurance.model.EventsRepo;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventResourceShould {

	private final EventsRepo eventsRepo = mock(EventsRepo.class);

	@Rule
	public final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new EventResource(eventsRepo))
			.build();

	private static final String EVENT_NAME = "Some name";
	private static final String EVENT_ARTIST = "Some artist";
	private static final String EVENT_GENRE = "Some genre";
	private static final String LOCATION = "Some location";
	private static final String EVENT_DATE = "2006-12-12";
	public static final int EVENT_NUMBER = 1;

	private final LocalDate eventDate = LocalDate.parse(EVENT_DATE, DateTimeFormatter.ISO_LOCAL_DATE);
	private final Event event = new Event(EVENT_NAME, EVENT_ARTIST, eventDate, EVENT_GENRE, LOCATION);

	@Test
	public void get_event() {
		given(eventsRepo.fetchEvent(EVENT_NUMBER)).willReturn(event);

		String response = resources
				.client()
				.target("/event/1")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get()
				.readEntity(String.class);

		System.out.println(response);

		assertThat(response).isEqualTo(EVENT_ARTIST);

		verify(eventsRepo).fetchEvent(EVENT_NUMBER);
	}
}