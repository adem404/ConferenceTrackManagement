package de.itemis.codingchallenge.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.itemis.codingchallenge.Talk;

public class TalkTest {

	@Test
	public void testTalk() {
		String name = "Example Talk";
		String duration = "45min";
		int expectedDuration = 45;
		Talk talk = new Talk(name, duration);
		assertTrue(talk.getName().equals(name) && (talk.getDuration() == expectedDuration));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTalkEmptyDuration() {
		String name = "Example Talk";
		String duration = "";
		new Talk(name, duration);
	}
}
