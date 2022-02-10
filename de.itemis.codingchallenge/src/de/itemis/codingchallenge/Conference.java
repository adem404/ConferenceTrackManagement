package de.itemis.codingchallenge;

import java.util.ArrayList;

public class Conference {

	public ArrayList<Track> trackList;

	public Conference() {
		super();
		this.trackList = new ArrayList<Track>();
	}

	public void addTrack(Track track) {
		trackList.add(track);

	}

}
