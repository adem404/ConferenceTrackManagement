package de.itemis.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import de.itemis.codingchallenge.Talk.Duration;

public class ConferenceTrackGenerator {

	public static final int DEFAULT_DURATION_TEXT_LENGTH = 5;
	public static final int TEXT_LENGTH_OF_MIN = 3;

	private Conference conference;
	private LinkedList<Talk> allTalks;

	public ConferenceTrackGenerator() {
		this.conference = new Conference();
		this.allTalks = new LinkedList<>();
	}
	
	public Conference generator(BufferedReader reader) throws IOException {
		String strCurrentLine;
		while ((strCurrentLine = reader.readLine()) != null) {

			if (strCurrentLine.contains(Duration.LIGHTENING.identifier)) {
				allTalks.add(new Talk(
						strCurrentLine.substring(0, strCurrentLine.length() - Duration.LIGHTENING.identifier.length()),
						strCurrentLine.substring(strCurrentLine.length() - Duration.LIGHTENING.identifier.length(),
								strCurrentLine.length())));

			} else {
				allTalks.add(
						new Talk(strCurrentLine.substring(0, strCurrentLine.length() - DEFAULT_DURATION_TEXT_LENGTH),
								strCurrentLine.substring(strCurrentLine.length() - DEFAULT_DURATION_TEXT_LENGTH,
										strCurrentLine.length() - TEXT_LENGTH_OF_MIN)));

			}

		}
		
		return conference;
		
	}

}
