package de.itemis.codingchallenge;

import java.util.ArrayList;

public class Track {

	private ArrayList<Session> sessionList;

	public Track() {
		super();
		this.sessionList = new ArrayList<Session>();
	}

	public void addSessionToTrack(Session session) {
		sessionList.add(session);
	}

}
