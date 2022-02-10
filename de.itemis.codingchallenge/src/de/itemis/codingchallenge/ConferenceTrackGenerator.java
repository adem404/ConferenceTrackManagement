package de.itemis.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConferenceTrackGenerator {

	public static final int DEFAULT_DURATION_TEXT_LENGTH = 5;
	public static final int TEXT_LENGTH_OF_MIN = 3;
	private final Pattern pattern = Pattern.compile("(.+)([0-9]{2}min|lightning)$");
	private Matcher matcher;

	public static final int TALKNAME_MATCHER_GROUP = 1;
	public static final int DURATION_MATCHER_GROUP = 2;
	public final boolean MORNING_SESSION = true;
	public final boolean AFTERNOON_SESSION = false;

	private Conference conference;
	private ArrayList<Talk> allTalks;

	public ConferenceTrackGenerator() {
		this.conference = new Conference();
		this.allTalks = new ArrayList<>();
	}

	public Conference generator(BufferedReader reader) throws IOException {
		String strCurrentLine;

		while ((strCurrentLine = reader.readLine()) != null) {
			matcher = pattern.matcher(strCurrentLine);
			if (matcher.find()) {
				allTalks.add(new Talk(matcher.group(TALKNAME_MATCHER_GROUP), matcher.group(DURATION_MATCHER_GROUP)));
			}
		}
		
		while(!allTalks.isEmpty()) {
			Track track = new Track();
			
			Session morningSession = new Session(MORNING_SESSION);
			addTalkToSession(morningSession, allTalks);
			morningSession.addEndSession();
			track.addSessionToTrack(morningSession);
			
			Session afternoonSession = new Session(AFTERNOON_SESSION);
			addTalkToSession(afternoonSession, allTalks);
			afternoonSession.addEndSession();
			track.addSessionToTrack(afternoonSession);
			
			conference.addTrack(track);

		}
		

		return conference;

	}
	
	private static void addTalkToSession(Session session, ArrayList<Talk> allTalks){
		for (int i = 0; i < allTalks.size(); i++) {
			Talk currentTalk = allTalks.get(i);
			if (session.hasFreePlace(currentTalk)) {
				session.addTalk(currentTalk);
				allTalks.remove(i);
				i--;
			}
		}
		
		
		
	}

}
