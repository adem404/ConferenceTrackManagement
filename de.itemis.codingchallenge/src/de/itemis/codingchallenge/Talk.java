package de.itemis.codingchallenge;

import java.util.Date;

/**
 * The Talk class represent an Event with given name and duration.
 */
public class Talk {

	public enum Duration {
		LIGHTENING(5, "lightning");

		String identifier;
		int value;

		Duration(int value, String identifier) {
			this.value = value;
			this.identifier = identifier;
		}
	}

	private String name;
	private String duration;
	private int durationInMinutes;
	private Date startTime;

	/**
	 * Constructor for a Talk with given duration
	 * 
	 * @param name
	 * @param duration
	 */
	public Talk(String name, String duration) {
		super();
		this.name = name;
		this.duration = duration;
		this.durationInMinutes = durationToMinutes(duration);
	}

	/**
	 * Constructor for a Talk with given start time
	 * 
	 * @param name
	 * @param startTime
	 */
	public Talk(String name, Date startTime) {
		super();
		this.name = name;
		this.startTime = startTime;
	}

	/**
	 * converts a parsed (String)duration to (Integer)minutes 
	 * example: "60min" ->60, "lightning" -> 5
	 * 
	 * @param duration
	 * @return minutes as Integer
	 */
	private int durationToMinutes(String duration) {
		if (duration != null && !duration.isEmpty()) {
			if (duration.toLowerCase().equals(Duration.LIGHTENING.identifier)) {
				return Duration.LIGHTENING.value;
			} else {
				return Integer.parseInt(duration.replace("min", ""));
			}
		}
		throw new IllegalArgumentException("Talk \"" + this.name + "\" missing duration");
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return durationInMinutes;
	}

	public Date getStartTime() {
		return startTime;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(name + duration);
		return s.toString();
	}
}