package com.codurance.model;

import java.time.LocalDate;

public class Gig {

	private final String name;
	private final String artist;
	private final LocalDate date;
	private final String location;

	public Gig(String name, String artist, LocalDate date, String location) {
		this.name = name;
		this.artist = artist;
		this.date = date;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getLocation() {
		return location;
	}
}
