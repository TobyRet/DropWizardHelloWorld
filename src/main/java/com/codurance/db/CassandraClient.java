package com.codurance.db;

import com.codurance.EventListerConfiguration;
import com.datastax.driver.core.*;
import io.dropwizard.setup.Environment;

import java.time.ZoneId;

public class CassandraClient {
	public static final ZoneId ZONE_ID = ZoneId.of("Europe/London");
	private Session session;

	public Session connect(EventListerConfiguration configuration, Environment environment) {
		return newSession(configuration, environment);
	}

	public void setUpDatabase() {
		databaseHealthCheck();
		createSchema();
	}

	public PreparedStatement prepare(String statement) {
		return session.prepare(statement);
	}

	public ResultSet execute(BoundStatement boundEvent) {
		return session.execute(boundEvent);
	}

	private Session newSession(EventListerConfiguration configuration, Environment environment) {
		session = configuration.getCassandraFactory().build(environment).connect();
		return session;
	}

	private void databaseHealthCheck() {
		Metadata metadata = session.getCluster().getMetadata();

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

}
