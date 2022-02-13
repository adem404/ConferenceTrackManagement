package de.itemis.codingchallenge.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import de.itemis.codingchallenge.Conference;
import de.itemis.codingchallenge.ConferenceTrackGenerator;

class ConferenceTrackManagementTest {

	/**
	 * Test for given original_input.txt as argument and expecting
	 * expected_original_output.txt
	 */
	@Test
	void testConferenceTrackManagementOriginalInput() {
		testConferenceTrackManagement("resources/original_input.txt", "resources/expected_original_output.txt");
	}

	/**
	 * Test for one Talk in less_input.txt as argument and expecting
	 * expected_less_output.txt
	 */
	@Test
	void testConferenceTrackManagementOneTalk() {
		testConferenceTrackManagement("resources/less_input.txt", "resources/expected_less_output.txt");
	}

	private void testConferenceTrackManagement(String inputFile, String expectedOutputFile) {
		Path input = Path.of(inputFile);
		Path expectedOutputPath = Paths.get(expectedOutputFile);
		BufferedReader reader;
		Conference conference;
		Path conferenceOutputPath;

		try {
			conferenceOutputPath = Files.createTempFile("conference", ".tmp");
			reader = new BufferedReader(new FileReader(input.toString()));
			conference = new ConferenceTrackGenerator().generator(reader);
			Files.writeString(conferenceOutputPath, conference.toString(), StandardCharsets.UTF_8);
			assertTrue(-1 == Files.mismatch(conferenceOutputPath, expectedOutputPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}