package com.codurance;

import com.codurance.helloworld.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class SampleApplication extends Application<SampleConfiguration> {

	public static void main(String[] args) throws Exception {
		new SampleApplication().run(args);
	}

	@Override
	public String getName() {
		return "HelloWorld";
	}

	@Override
	public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
		bootstrap.addBundle(new ViewBundle());
	}

	@Override
	public void run(SampleConfiguration sampleConfiguration, Environment environment) throws Exception {
		environment.jersey().register(new HelloWorldResource());
	}
}