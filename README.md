## Conference Track Management

### Prerequisites for Building and Executing the Program

* Java 17 or above

### How to Build

```bash
cd /path/to/project-root
javac src/de/itemis/codingchallenge/*.java -d classes
```

### Syntax of input_file.txt

[Titel of a Talk] [duration in minutes]min | lightning 

Example:
```bash
Accounting-Driven Development 45min
Rails for Python Developers lightning
A World Without HackerNews 30min
.
.
.
```

### How to Run the Program

```bash
java -cp classes de.itemis.codingchallenge.ConferenceTrackManagement /path/to/input_file.txt
```

### Design

#### ConferenceScheduler

* The `ConferenceTrackGenerator` class provides a `generator()` method API to create a
  `Conference` object representing a scheduled conference with tracks for the provided input
* To generate the conference, this programm first collects all talks and fits them afterwards  
  in a session of a track. The remaining time of a session is the decider for accepting a talk to a session.
* Example use of the API:
```java
BufferedReader reader = new BufferedReader(new FileReader("/path/to/input_file"));
ConferenceTrackGenerator ctg = new ConferenceTrackGenerator();
Conference conference = ctg.generator(reader);
System.out.println(conference);
```

#### Conference

* A Conference contains multiple Tracks

#### Track

* A Track describes a day of a Conference

#### Session

* A Session represents a morning or afternoon session with a list of talks

#### Talk

* The Talk class represent an Event with given name and duration.