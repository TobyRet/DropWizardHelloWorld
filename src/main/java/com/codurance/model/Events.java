package com.codurance.model;

import com.codurance.db.CassandraClient;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;

import java.util.Date;

public class Events {

	private final CassandraClient cassandraClient;
	private final PreparedStatement addEvent;

	public Events(CassandraClient cassandraClient) {
		this.cassandraClient = cassandraClient;
		this.addEvent = cassandraClient.prepare(
				"INSERT INTO gig_listings.events" +
						" (name, artist, date, genre, location)" +
						" VALUES(?, ?, ?, ?, ?);");
	}

	public void add(Event event) {
		Date eventDate = Date.from(event.getDate().atStartOfDay(CassandraClient.ZONE_ID).toInstant());
		BoundStatement boundEvent = new BoundStatement(addEvent).bind(event.getName(), event.getArtist(), eventDate, event.getGenre(), event.getLocation());
		cassandraClient.execute(boundEvent);
	}

}
