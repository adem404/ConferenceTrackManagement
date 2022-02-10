package de.itemis.codingchallenge;


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
	private int duration;

	public Talk(String name, String duration) {
		super();
		this.name = name;
		this.duration = durationToMinutes(duration);
	}

	private int durationToMinutes(String duration) {
		if (duration != null && !duration.isEmpty()) {
			if (duration.toLowerCase().equals(Duration.LIGHTENING.identifier)) {
				return Duration.LIGHTENING.value;
			} else {
				duration = duration.replace("min", "");
				return Integer.parseInt(duration);
			}
		}
		throw new IllegalArgumentException("Talk \""+this.name+"\" missing duration");
	}


	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

}
