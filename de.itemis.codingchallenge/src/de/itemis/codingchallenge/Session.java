package de.itemis.codingchallenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A Session represents a morning or afternoon session with a list of talks
 */
public class Session {

	private static final int MAX_MORNING_DURATION = 180;
	private static final int MAX_AFTERNOON_DURATION = 240;
	private static final String MORNING_SESSION_STARTTIME = "09:00AM";
	public static final boolean MORNING_SESSION = true;
	public static final boolean AFTERNOON_SESSION = false;
	private static final String AFTERNOON_SESSION_STARTTIME = "01:00PM";
	private static final String LUNCH = "Lunch";
	private static final String LUNCH_STARTTIME = "12:00PM";
	private static final String NETWORKING = "Networking Event";
	private static final String NETWORKING_STARTTIME = "05:00PM";

	private boolean morningSession;
	private int remainingTime;
	private ArrayList<Talk> talkList;
	private Talk endEvent;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mmaa");
	private String sessionStartTime;

	/**
	 * Constructor for a Session
	 * 
	 * @param morningSession for switching between morning and afternoon session
	 */
	public Session(boolean morningSession) {
		super();
		this.morningSession = morningSession;
		this.talkList = new ArrayList<>();
		this.remainingTime = morningSession ? MAX_MORNING_DURATION : MAX_AFTERNOON_DURATION;
		this.sessionStartTime = morningSession ? MORNING_SESSION_STARTTIME : AFTERNOON_SESSION_STARTTIME;
	}

	public boolean isMorningSession() {
		return morningSession;
	}

	public boolean hasFreePlace(Talk talk) {
		return remainingTime >= talk.getDuration();
	}

	public void addTalk(Talk currentTalk) {
		talkList.add(currentTalk);
		remainingTime -= currentTalk.getDuration();
	}

	/**
	 * includes an ending event for a session depending on morningSession a Lunch or
	 * Networking Event will be added
	 */
	public void addEndSession() {
		try {
			if (morningSession) {
				endEvent = new Talk(LUNCH, dateFormat.parse(LUNCH_STARTTIME));
			} else {
				endEvent = new Talk(NETWORKING, dateFormat.parse(NETWORKING_STARTTIME));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		String talkStartTime = null;

		// convert sessionStartTime to (hh:mmaa) format
		try {
			date = dateFormat.parse(sessionStartTime);
			sessionStartTime = dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// iterating through talkList a session
		for (int i = 0; i < talkList.size(); i++) {
			if (i == 0) {
				// initial session start time
				talkStartTime = sessionStartTime;
			}
			if (date != null && talkStartTime != null && i > 0) {
				// adding the duration of previous talk to the start time of the next talk
				calendar.setTime(date);
				calendar.add(Calendar.MINUTE, talkList.get(i - 1).getDuration());
				date = calendar.getTime();
				talkStartTime = dateFormat.format(date);
			}
			s.append(talkStartTime + " " + talkList.get(i) + System.getProperty("line.separator"));
		}
		// adding end event after all talks of a session was processed
		s.append(dateFormat.format(endEvent.getStartTime()) + " " + endEvent.getName()
				+ System.getProperty("line.separator"));
		return s.toString();
	}
}