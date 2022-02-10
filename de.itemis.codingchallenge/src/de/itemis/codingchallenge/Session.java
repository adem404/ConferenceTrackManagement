package de.itemis.codingchallenge;

import java.util.ArrayList;

public class Session {

	private boolean morningSession;
	private int remainingTime;
	private ArrayList<Talk> talkList;
	private static final int MAX_MORNING_DURATION = 180;
	private static final int MAX_AFTERNOON_DURATION = 240;
	private static final int LUNCH_STARTTIME = 720;
	private static final int LUNCH_DURATION = 60;
	private static final int NETWORKING_STARTTIME = 1020;
	private static final int NETWORKING_DURATION = 60;
	private static final String LUNCH = "Lunch";
	private static final String NETWORKING = "Networking Event";
	private Talk endEvent;

	public Session(boolean morningSession) {
		super();
		this.morningSession = morningSession;
		this.talkList = new ArrayList<>();
		this.remainingTime = morningSession ? MAX_MORNING_DURATION : MAX_AFTERNOON_DURATION;
	}

	public boolean isMorningSession() {
		return morningSession;
	}

	public void setMorningSession(boolean morningSession) {
		this.morningSession = morningSession;
	}

	public boolean hasFreePlace(Talk talk) {
		return remainingTime >= talk.getDuration();
	}

	public void addTalk(Talk currentTalk) {
		talkList.add(currentTalk);
		remainingTime -= currentTalk.getDuration();
	}

	public void addEndSession() {
		if (morningSession) {
			endEvent = new Talk(LUNCH, String.valueOf(LUNCH_DURATION));
		} else {
			endEvent = new Talk(NETWORKING, String.valueOf(LUNCH_DURATION));
		}
	}

}
