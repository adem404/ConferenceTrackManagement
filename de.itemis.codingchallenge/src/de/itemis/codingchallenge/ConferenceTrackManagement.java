/**
 * 
 */
package de.itemis.codingchallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ConferenceTrackManagement schedules multiple talks into the time
 * constraints of a day.
 * 
 * @author Adem Dagdeviren
 */
public class ConferenceTrackManagement {

	/**
	 * @param args path for text file with talks and their duration
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("No valid arguments!");
			System.exit(1);
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			ConferenceTrackGenerator ctg = new ConferenceTrackGenerator();
			Conference conference = ctg.generator(reader);
			System.out.println(conference);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}