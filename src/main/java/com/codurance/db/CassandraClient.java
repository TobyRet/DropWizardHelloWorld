package com.codurance.db;

import com.codurance.model.Gig;
import com.datastax.driver.core.*;

import java.time.ZoneId;
import java.util.Date;

public class CassandraClient {
	public static final ZoneId ZONE_ID = ZoneId.of("Europe/London");
	private Cluster cluster;
	private Session session;
	private PreparedStatement addGig;

	public void connect(String node) {
		cluster = Cluster.builder()
				.addContactPoint(node)
				.build();

		Metadata metadata = cluster.getMetadata();

		System.out.printf("Connected to cluster: %s%n", metadata.getClusterName());

		for (Host host : metadata.getAllHosts()) {
			System.out.printf("Datacentre: %s%n; Host: %s%n; Rack: %s%n",
					host.getDatacenter(),
					host.getAddress(),
					host.getRack());
		}

		session = cluster.connect();
		createSchema();
	}


	private void createSchema() {
		session.execute("CREATE KEYSPACE IF NOT EXISTS gig_listings WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};");
		session.execute(
				"CREATE TABLE IF NOT EXISTS gig_listings.events (" +
						"name text," +
						"artist text," +
						"date timestamp," +
						"location text," +
						"PRIMARY KEY (date, location)" +
						");");

		addGig = session.prepare(
				"INSERT INTO gig_listings.events" +
				" (name, artist, date, location)" +
				" VALUES(?, ?, ?, ?);");
	}

	public void close() {
		cluster.close();
	}

	public void add(Gig gig) {
		Date gigDate = Date.from(gig.getDate().atStartOfDay(ZONE_ID).toInstant());
		session.execute(new BoundStatement(addGig).bind(gig.getName(), gig.getArtist(), gigDate, gig.getLocation()));
	}
}