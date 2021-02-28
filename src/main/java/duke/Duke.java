package duke;

import duke.taskclass.Deadlines;
import duke.taskclass.Events;
import duke.taskclass.Task;
import duke.taskclass.Todo;

import java.util.Scanner;

public class Duke {
    static final int MAX_TASK = 100;
    static String userCommand;
    static int taskCounter=0;

    static Task[] tasks = new Task[MAX_TASK];

    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        printHorizontal();
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        printHorizontal();

        userCommand = (input.nextLine()).trim();

        while(!(userCommand.equals("bye"))) {
            printHorizontal();
            if(userCommand.equals("list")) {
                listTasks(tasks);
            } else if(userCommand.contains("done")) {
                markTasks(tasks);
            } else if(userCommand.contains("todo")) {
                addTodo(tasks);
            } else if(userCommand.contains("deadline")){
                addDeadline(tasks);
            } else if(userCommand.contains("event")) {
                addEvent(tasks);
            } else {
                System.out.println("Oops! Command is not valid.");
            }
            printHorizontal();
            userCommand = (input.nextLine()).trim();
        }

        printHorizontal();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public static void listTasks(Task[] tasks){
        if(taskCounter==0) {
            System.out.println("No tasks are there.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int j = 0; j < taskCounter; ++j) {
                System.out.println("        " + (j + 1) + ". " + tasks[j].toString());
            }
        }
    }

    //This function marks the tasks as done.
    public static void markTasks(Task[] tasks){

        int taskIndex, taskNumAsInt;
        try {
            String taskNumAsString = userCommand.substring(5);
            taskNumAsInt = Integer.parseInt(taskNumAsString);
            taskIndex = taskNumAsInt - 1;
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    "        " + taskNumAsInt + ". " + tasks[taskIndex].toString());
        } catch (NumberFormatException e) {
            System.out.println("Task to be marked done is not specified as a number.");
        } catch (NullPointerException e) {
            System.out.println("There are no such tasks added yet.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task Number field is either empty or out of bounds.");
        }
    }

    public static void addTodo(Task[] tasks){

        try {
            String[] splitCommand = userCommand.split(" ", 2);
            tasks[taskCounter++] = new Todo(splitCommand[1]);
            printDetails(tasks);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You have not given any description.");
            --taskCounter;
        }
    }

    public static void addDeadline(Task[] tasks){
        String[] splitCommand = userCommand.split(" ", 2);
        String[] splitCommandAgain = splitCommand[1].split(" /by ");
        tasks[taskCounter++] = new Deadlines(splitCommandAgain[0], splitCommandAgain[1]);
        printDetails(tasks);
    }

    public static void addEvent(Task[] tasks){
        String[] splitCommand = userCommand.split(" ", 2);
        String[] splitCommandAgain = splitCommand[1].split(" /at ");
        tasks[taskCounter++] = new Events(splitCommandAgain[0], splitCommandAgain[1]);
        printDetails(tasks);
    }

    public static void printDetails(Task[] tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("        " + taskCounter + ". " + tasks[taskCounter-1].toString());
        System.out.println("Now you have "+taskCounter+ " tasks in the list.");
    }

    public static void printHorizontal(){
        String horizontalLine = "------------------------------------------";
        System.out.println(horizontalLine);
    }
}
