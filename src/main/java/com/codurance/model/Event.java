package com.codurance.model;

import java.time.LocalDate;

public class Event {

	private final String name;
	private final String artist;
	private final LocalDate date;
	private final String genre;
	private final String location;

	public Event(String name, String artist, LocalDate date, String genre, String location) {
		this.name = name;
		this.artist = artist;
		this.date = date;
		this.genre = genre;
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

	public String getGenre() {
		return genre;
	}

	public String getLocation() {
		return location;
	}
}
