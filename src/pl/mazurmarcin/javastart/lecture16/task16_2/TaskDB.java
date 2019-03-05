package pl.mazurmarcin.javastart.lecture16.task16_2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class TaskDB {

	private List<Task> tasks = new ArrayList<>();
	private final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm";
	private final String DATE_FORMAT_2 = "dd.MM.yyyy HH:mm";

	public List<Task> getTasks() {
		return tasks;
	}

	public boolean isDateFormatCorrect(String deadline) {

		LocalDateTime localDateTime = convertStringToLocalDateTime(deadline);
		return (localDateTime != null) ? true : false;

	}

	public void addTask(String description, String deadline) {

		LocalDateTime localDateTime = convertStringToLocalDateTime(deadline);
		Task task = new Task(description, localDateTime);
		tasks.add(task);

	}

	private LocalDateTime convertStringToLocalDateTime(String deadline) {

		LocalDateTime localDateTime = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_1);

		try {
			localDateTime = LocalDateTime.parse(deadline, dateFormat);
			return localDateTime;
		} catch (DateTimeParseException exception) {
			try {
				dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_2);
				localDateTime = LocalDateTime.parse(deadline, dateFormat);
				return localDateTime;
			} catch (DateTimeParseException exception2) {
				return null;
			}
		}
	}

	public List<Task> getOldTasks() {

		List<Task> oldTasks = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();

		for (Task task : tasks) {
			if (task.getDeadline().isBefore(now))
				oldTasks.add(task);
		}

		return oldTasks;
	}

	public List<Task> getTasksNext24h() {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime range = now.plusHours(24);
		List<Task> recentTasks = new ArrayList<>();

		for (Task task : tasks) {
			if (task.getDeadline().isBefore(range) && task.getDeadline().isAfter(now))
				recentTasks.add(task);
		}

		return recentTasks;
	}

	public List<Task> getTasksThisWeek() {

		LocalDate today = LocalDate.now();
		LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		
		List<Task> recentTasks = new ArrayList<>();

		for (Task task : tasks) {

			LocalDate taskDeadline = task.getDeadline().toLocalDate();

			if ((taskDeadline.isAfter(today) || taskDeadline.isEqual(today))
					&& (taskDeadline.isBefore(sunday) || taskDeadline.isEqual(sunday)))
				recentTasks.add(task);

		}

		return recentTasks;
	}

	public List<Task> getTasksNext30Days() {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime range = now.plusDays(30);
		List<Task> recentTasks = new ArrayList<>();

		for (Task task : tasks) {
			if (task.getDeadline().isBefore(range) && task.getDeadline().isAfter(now))
				recentTasks.add(task);
		}

		return recentTasks;
	}

}
