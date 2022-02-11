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

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < trackList.size(); i++) {
			s.append("Track " + (i + 1) + ":" + "\n");
			s.append(trackList.get(i).toString() + "\n");
		}
		return s.toString();
	}

}
