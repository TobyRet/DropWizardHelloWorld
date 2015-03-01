package com.codurance;

import com.codurance.model.EventsRepo;
import com.codurance.resources.EventsResource;
import com.datastax.driver.core.Session;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class EventListerApplication extends Application<EventListerConfiguration> {

	public static void main(String[] args) throws Exception {
		new EventListerApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<EventListerConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets"));
		bootstrap.addBundle(new ViewBundle());
	}

	@Override
	public void run(EventListerConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(eventsResource(configuration, environment));
	}

	private EventsResource eventsResource(EventListerConfiguration configuration, Environment environment) {
		return new EventsResource(eventsRepo(configuration, environment));
	}

	private EventsRepo eventsRepo(EventListerConfiguration configuration, Environment environment) {
		return new EventsRepo(session(configuration, environment));
	}

	private Session session(EventListerConfiguration configuration, Environment environment) {
		return configuration.getCassandraFactory().build(environment).connect();
	}
}
