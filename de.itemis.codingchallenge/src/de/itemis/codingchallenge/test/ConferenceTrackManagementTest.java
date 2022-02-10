package de.itemis.codingchallenge.test;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.io.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import de.itemis.codingchallenge.Conference;
import de.itemis.codingchallenge.ConferenceTrackGenerator;

class ConferenceTrackManagementTest {

	@Test
	void testConferenceTrackManagement() throws IOException {

		Path input = Path.of("resources/original_input.txt");

		BufferedReader reader = new BufferedReader(new FileReader(input.toString()));
		Conference conference = new ConferenceTrackGenerator().generator(reader);

		File conferenceOutput = new File("resources/conference.txt");
		File expectedOutput = new File("resources/original_output.txt");

		FileUtils.writeStringToFile(conferenceOutput, conference.toString());

		assertTrue(FileUtils.contentEquals(conferenceOutput, expectedOutput));
	}

}
