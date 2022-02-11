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

	@Test
	void testConferenceTrackManagement() throws IOException {

		Path input = Path.of("resources/original_input.txt");

		BufferedReader reader = new BufferedReader(new FileReader(input.toString()));
		Conference conference = new ConferenceTrackGenerator().generator(reader);
		Path conferenceOutputPath = Paths.get("resources/conference.txt");
		Path expectedOutputPath = Paths.get("resources/expected_output.txt");
		Files.writeString(conferenceOutputPath, conference.toString(), StandardCharsets.UTF_8);
		long mismatch = Files.mismatch(conferenceOutputPath, expectedOutputPath);
		assertTrue(mismatch == -1);
	}

}
