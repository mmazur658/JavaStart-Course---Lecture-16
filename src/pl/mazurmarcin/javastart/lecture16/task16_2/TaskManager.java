package pl.mazurmarcin.javastart.lecture16.task16_2;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

	private Scanner scanner = new Scanner(System.in);
	TaskDB taskDB = new TaskDB();

	public void runTaskManager() {

		System.out.println("System zarządzania wiadomościami - wybierz jedną z opcji: ");
		int userInput = 999;

		TaskManagerOption option;
		do {

			printMainOptions();
			userInput = getCorrectInt();
			option = TaskManagerOption.values()[userInput - 1];

			if (option != null) {
				switch (option) {
				case ADD_NEW_TASK:
					addNewTask();
					break;
				case SHOW_ALL_TASKS:
					showAllTasks();
					break;
				case SHOW_OLD_TASKS:
					showOldTasks();
					break;
				case SHOW_RECENT_TASKS:
					showRecentTasks();
					break;
				case EXIT:
					sayGoodBye();
					break;
				default:
					System.out.println("Wybór nieznany - spóbuj ponownie");
				}
			} else
				System.out.println("Wybór nieznany - spóbuj ponownie");

		} while (option != TaskManagerOption.EXIT);

	}

	private void sayGoodBye() {
		System.out.println("Dowidzenia");
		scanner.close();

	}

	private void showRecentTasks() {

		System.out.println("Zadania do wykonania w najbliższych 24h: ");
		List<Task> tasksNext24h = taskDB.getTasksNext24h();
		Collections.sort(tasksNext24h);
		printList(tasksNext24h);

		System.out.println("Zadania do wykonania w tym tygodniu: ");
		List<Task> tasksThisWeek = taskDB.getTasksThisWeek();
		Collections.sort(tasksThisWeek);
		printList(tasksThisWeek);

		System.out.println("Zadania do wykonania w najbliższych 30 dniach: ");
		List<Task> tasksNext30Days = taskDB.getTasksNext30Days();
		Collections.sort(tasksNext30Days);
		printList(tasksNext30Days);

	}

	private void showOldTasks() {

		List<Task> tasks = taskDB.getOldTasks();
		Collections.sort(tasks);
		printList(tasks);
	}

	private void showAllTasks() {

		List<Task> tasks = taskDB.getTasks();
		Collections.sort(tasks);
		printList(tasks);

	}

	private void printList(List<Task> tasks) {

		if (tasks == null || tasks.size() < 1)
			System.out.println("Brak zadań");
		else {
			for (int i = 0; i < tasks.size(); i++)
				System.out.println((i + 1) + " - " + tasks.get(i));
		}

		System.out.println();

	}

	private void addNewTask() {

		scanner.nextLine();
		System.out.println("Podaj opis: ");
		String description = scanner.nextLine();

		System.out.println("Podaj termin w formacie: rrrr-mm-dd gg:mm lub dd.mm.rrrr gg:mm");
		String deadline = scanner.nextLine();

		if (taskDB.isDateFormatCorrect(deadline)) {
			taskDB.addTask(description, deadline);
			System.out.println("Zadanie zostało dodane");
		} else {
			System.out.println("Nieprawidłowy format daty");
		}

	}

	private void printMainOptions() {
		System.out.println((TaskManagerOption.ADD_NEW_TASK.ordinal() + 1) + " - Dodaj nowe zadanie");
		System.out.println((TaskManagerOption.SHOW_ALL_TASKS.ordinal() + 1) + " - Pokaż wszystkie zadania");
		System.out.println((TaskManagerOption.SHOW_OLD_TASKS.ordinal() + 1) + " - Pokaż archiwalne zadania");
		System.out.println((TaskManagerOption.SHOW_RECENT_TASKS.ordinal() + 1) + " - Pokaż aktualne zadania");
		System.out.println((TaskManagerOption.EXIT.ordinal() + 1) + " - Wyjście");

	}

	public int getCorrectInt() {

		int number = 0;
		boolean isNumberInt = false;

		do {
			try {
				number = scanner.nextInt();
				isNumberInt = true;
			} catch (InputMismatchException exception) {
				System.out.println("Nieprawidłowy format danych");
				scanner.nextLine();
			}
		} while (!isNumberInt);

		return number;

	}

}
