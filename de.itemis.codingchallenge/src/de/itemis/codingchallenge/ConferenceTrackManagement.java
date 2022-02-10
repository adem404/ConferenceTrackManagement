/**
 * 
 */
package de.itemis.codingchallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author Ad
 *
 */
public class ConferenceTrackManagement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("No valid arguments!");
			System.exit(1);
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			ConferenceTrackGenerator ctg = new ConferenceTrackGenerator();
			ctg.generator(reader);
			System.out.println("Finish");
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
