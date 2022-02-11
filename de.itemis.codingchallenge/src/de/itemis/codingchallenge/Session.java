package de.itemis.codingchallenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Session {

	private boolean morningSession;
	private int remainingTime;
	private ArrayList<Talk> talkList;
	private static final int MAX_MORNING_DURATION = 180;
	private static final int MAX_AFTERNOON_DURATION = 240;
	private static final String MORNING_SESSION_STARTTIME = "09:00AM";
	private static final String AFTERNOON_SESSION_STARTTIME = "01:00PM";
	private static final String LUNCH_STARTTIME = "12:00PM";
	private static final String NETWORKING_STARTTIME = "05:00PM";
	private static final String LUNCH = "Lunch";
	private static final String NETWORKING = "Networking Event";
	private Talk endEvent;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mmaa");

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
		String sessionStartTime = morningSession ? MORNING_SESSION_STARTTIME : AFTERNOON_SESSION_STARTTIME;
		Date date = null;
		String talkStartTime = null;
		try {
			date = dateFormat.parse(sessionStartTime);
			sessionStartTime = dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < talkList.size(); i++) {
			if (i == 0) {
				talkStartTime = sessionStartTime;
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.MINUTE, talkList.get(i - 1).getDuration());
				date = calendar.getTime();
				talkStartTime = dateFormat.format(date);

			}

			s.append(talkStartTime + " " + talkList.get(i) + "\n");
		}
		s.append(dateFormat.format(endEvent.getStartTime()) + " " + endEvent.getName() + "\n");
		return s.toString();
	}
}
