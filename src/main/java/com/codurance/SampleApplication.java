package com.codurance;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SampleApplication extends Application<SampleConfiguration> {

	public static void main(String[] args) throws Exception {
		new SampleApplication().run(args);
	}

	@Override
	public String getName() {
		return "myapp";
	}

	@Override
	public void initialize(Bootstrap<SampleConfiguration> bootstrap) {

	}

	@Override
	public void run(SampleConfiguration sampleConfiguration, Environment environment) throws Exception {
		environment.jersey().register(new HelloWorldResource());
	}
}