package de.itemis.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates a conference
 */
public class ConferenceTrackGenerator {

	private final Pattern pattern = Pattern.compile("(.+)([0-9]{2}min|lightning)$");
	public static final int TALKNAME_MATCHER_GROUP = 1;
	public static final int DURATION_MATCHER_GROUP = 2;
	private Matcher matcher;
	private Conference conference;
	private ArrayList<Talk> allTalks;

	public ConferenceTrackGenerator() {
		this.conference = new Conference();
		this.allTalks = new ArrayList<>();
	}

	/**
	 * collecting all Talks and scheduling a conference them multiple session and
	 * tracks
	 * 
	 * @param reader
	 * @return Conference with Tracks of Sessions and Talks
	 * @throws IOException
	 */
	public Conference generator(BufferedReader reader) throws IOException {
		String strCurrentLine;

		// collecting all talks
		while ((strCurrentLine = reader.readLine()) != null) {
			matcher = pattern.matcher(strCurrentLine);
			if (matcher.find()) {
				// splitting the name and the duration of a talk
				allTalks.add(new Talk(matcher.group(TALKNAME_MATCHER_GROUP), matcher.group(DURATION_MATCHER_GROUP)));
			}
		}

		// processing all talks
		while (!allTalks.isEmpty()) {
			Track track = new Track();

			Session morningSession = new Session(Session.MORNING_SESSION);
			addTalkToSession(morningSession, allTalks);
			morningSession.addEndSession();
			track.addSessionToTrack(morningSession);

			Session afternoonSession = new Session(Session.AFTERNOON_SESSION);
			addTalkToSession(afternoonSession, allTalks);
			afternoonSession.addEndSession();
			track.addSessionToTrack(afternoonSession);

			conference.addTrack(track);
		}
		return conference;
	}

	/**
	 * adding a Talk to a Session if there is enough remaining time
	 * 
	 * @param session
	 * @param allTalks
	 */
	private static void addTalkToSession(Session session, ArrayList<Talk> allTalks) {
		for (int i = 0; i < allTalks.size(); i++) {
			Talk currentTalk = allTalks.get(i);
			if (session.hasFreePlace(currentTalk)) {
				session.addTalk(currentTalk);
				allTalks.remove(i);
				// decrement because remove() shifts any subsequent element to the left
				i--;
			}
		}
	}
}