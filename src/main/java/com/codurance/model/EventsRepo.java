package com.codurance.model;

import com.codurance.db.CassandraClient;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import java.util.Date;

public class EventsRepo {

	private final Session cassandraClient;
	private final PreparedStatement addEvent;

	public EventsRepo(Session cassandraClient) {
		this.cassandraClient = cassandraClient;
		this.addEvent = cassandraClient.prepare(
				"INSERT INTO gig_listings.events" +
						" (name, artist, date, genre, location)" +
						" VALUES(?, ?, ?, ?, ?);");
	}

	public void add(Event event) {
		Date eventDate = Date.from(event.getDate().atStartOfDay(CassandraClient.ZONE_ID).toInstant());
		BoundStatement boundEvent = new BoundStatement(addEvent).bind(
													event.getName(),
													event.getArtist(),
													eventDate,
													event.getGenre(),
													event.getLocation());
		cassandraClient.execute(boundEvent);
	}
}
