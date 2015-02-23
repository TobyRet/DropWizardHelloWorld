package com.codurance.db;

import com.datastax.driver.core.*;

import java.time.ZoneId;

public class CassandraClient {
	public static final ZoneId ZONE_ID = ZoneId.of("Europe/London");
	private Cluster cluster;
	private Session session;

	public void connect(String node) {
		cluster = Cluster.builder()
				.addContactPoint(node)
				.build();

		databaseHealthCheck();

		session = cluster.connect();
		createSchema();
	}

	private void databaseHealthCheck() {
		Metadata metadata = cluster.getMetadata();

		System.out.printf("Connected to cluster: %s%n", metadata.getClusterName());

		for (Host host : metadata.getAllHosts()) {
			System.out.printf("Datacentre: %s; Host: %s; Rack: %s%n",
					host.getDatacenter(),
					host.getAddress(),
					host.getRack());
		}
	}


	private void createSchema() {
		session.execute("CREATE KEYSPACE IF NOT EXISTS gig_listings WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};");
		session.execute(
				"CREATE TABLE IF NOT EXISTS gig_listings.events (" +
						"name text," +
						"artist text," +
						"date timestamp," +
						"genre text," +
						"location text," +
						"PRIMARY KEY (date, location)" +
						");");
	}

	public PreparedStatement prepare(String statement) {
		return session.prepare(statement);
	}

	public ResultSet execute(BoundStatement boundEvent) {
		return session.execute(boundEvent);
	}

	public void close() {
		cluster.close();
	}
}
