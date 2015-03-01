package com.codurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.jackson.JsonSnakeCase;
import org.stuartgunter.dropwizard.cassandra.CassandraFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonSnakeCase
public class EventListerConfiguration extends Configuration{

	@Valid
	@NotNull
	private CassandraFactory cassandra = new CassandraFactory();

	@JsonProperty("cassandra")
	public CassandraFactory getCassandraFactory() {
		return cassandra;
	}

	@JsonProperty("cassandra")
	public void setCassandraFactory(CassandraFactory cassandra) {
		this.cassandra = cassandra;
	}
}