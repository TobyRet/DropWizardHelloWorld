package com.codurance;

import com.codurance.db.CassandraClient;
import com.codurance.model.EventsRepo;
import com.codurance.resources.EventResource;
import com.codurance.resources.EventsResource;
import com.datastax.driver.core.Session;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;

public class EventListerApplication extends Application<EventListerConfiguration> {

	private CassandraClient cassandraClient;
	private EventsRepo eventsRepo;

	public static void main(String[] args) throws Exception {
		new EventListerApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<EventListerConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets"));
		bootstrap.addBundle(new ViewBundle());
		bootstrap.addBundle(new SwaggerBundle<EventListerConfiguration>());
	}

	@Override
	public void run(EventListerConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(eventResource(configuration, environment));
		environment.jersey().register(eventsResource(configuration, environment));
		cassandraClient.setUpDatabase();
	}

	private EventsResource eventsResource(EventListerConfiguration configuration, Environment environment) {
		return new EventsResource(eventsRepo(configuration, environment));
	}

	private EventResource eventResource(EventListerConfiguration configuration, Environment environment) {
		return new EventResource(eventsRepo(configuration, environment));
	}

	private EventsRepo eventsRepo(EventListerConfiguration configuration, Environment environment) {
		eventsRepo = new EventsRepo(session(configuration, environment));
		return eventsRepo;
	}

	private Session session(EventListerConfiguration configuration, Environment environment) {
		cassandraClient = new CassandraClient();
		return cassandraClient.connect(configuration, environment);
	}
}
