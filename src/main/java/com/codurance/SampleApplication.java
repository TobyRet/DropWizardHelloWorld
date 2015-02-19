package com.codurance;

import com.codurance.db.CassandraClient;
import com.codurance.resources.GigResource;
import com.codurance.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class SampleApplication extends Application<SampleConfiguration> {

	public static void main(String[] args) throws Exception {
		new SampleApplication().run(args);
		CassandraClient client = new CassandraClient();
		client.connect("127.0.0.1");
		client.close();
	}

	@Override
	public String getName() {
		return "HelloWorld";
	}

	@Override
	public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets"));
		bootstrap.addBundle(new ViewBundle());
	}

	@Override
	public void run(SampleConfiguration sampleConfiguration, Environment environment) throws Exception {
		environment.jersey().register(new HelloWorldResource());
		environment.jersey().register(new GigResource());
	}
}