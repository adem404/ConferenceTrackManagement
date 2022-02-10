package de.itemis.codingchallenge;

import java.util.LinkedList;

public class Session {

	private boolean morningSession;
	private int allocatedTime;
	private LinkedList<Talk> talkList;
	private static final int MAX_MORNING_DURATION = 180;
	private static final int MAX_AFTERNOON_DURATION = 240;

	public Session(boolean morningSession, LinkedList<Talk> talkList) {
		super();
		this.morningSession = morningSession;
		this.talkList = talkList;
	}

	public boolean isMorningSession() {
		return morningSession;
	}

	public void setMorningSession(boolean morningSession) {
		this.morningSession = morningSession;
	}

	public LinkedList<Talk> getTalkList() {
		return talkList;
	}

	public void setTalkList(LinkedList<Talk> talkList) {
		this.talkList = talkList;
	}

}
