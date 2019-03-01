package pl.mazurmarcin.javastart.lecture16.task16_2;

import java.time.LocalDateTime;

public class Task implements Comparable<Task> {

	private String description;
	private LocalDateTime deadline;

	public Task(String description, LocalDateTime deadline) {
		this.description = description;
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "Opis: " + description + ", termin: " + deadline;
	}

	@Override
	public int compareTo(Task o) {

		if (this.deadline.isAfter(o.getDeadline()))
			return 1;
		else if (this.deadline.isBefore(o.getDeadline()))
			return -1;
		else
			return 0;

	}

}
