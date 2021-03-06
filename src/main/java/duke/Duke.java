package duke;

import duke.taskclass.Deadlines;
import duke.taskclass.Events;
import duke.taskclass.Task;
import duke.taskclass.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final int MAX_TASK = 100;
    static int taskCounter = 0;
    static String userCommand;

   static ArrayList<Task> tasks = new ArrayList<>();

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
            } else if(userCommand.contains("delete")){
                deleteTasks(tasks);
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

    public static void listTasks(ArrayList<Task> tasks){
        if(tasks.size()==0) {
            System.out.println("No tasks are there.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task taskObject: tasks ) {
                System.out.println("        " + (tasks.indexOf(taskObject) + 1) + ". " + taskObject.toString());
            }
        }
    }

    //This function marks the tasks as done.
    public static void markTasks(ArrayList<Task> tasks){

        int taskIndex, taskNumAsInt;
        try {
            String taskNumAsString = userCommand.substring(5);
            taskNumAsInt = Integer.parseInt(taskNumAsString);
            taskIndex = taskNumAsInt - 1;
            tasks.get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    "        " + taskNumAsInt + ". " + tasks.get(taskIndex).toString());
        } catch (NumberFormatException e) {
            System.out.println("Task to be marked done is not specified as a number.");
        } catch (NullPointerException e) {
            System.out.println("There are no such tasks added yet.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task Number field is either empty or out of bounds.");
        }
    }

    public static void deleteTasks(ArrayList<Task> tasks){
        int taskNumAsInt;
        try{
            String[] splitCommand = userCommand.split(" ", 2);
            taskNumAsInt = Integer.parseInt(splitCommand[1]);
            System.out.println("Noted! I've removed this task:" + System.lineSeparator() +
                    "        " + taskNumAsInt + ". " + tasks.get(taskNumAsInt-1).toString());
            tasks.remove(taskNumAsInt-1);
            --taskCounter;
            System.out.println("Now you have "+tasks.size()+" tasks in your list.");
        } catch (NumberFormatException e) {
            System.out.println("Task to be deleted is not specified as a number.");
        } catch (NullPointerException e) {
            System.out.println("There are no such tasks yet.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task Number field is either empty or out of bounds.");
        }
    }

    public static void addTodo(ArrayList<Task> tasks){

        try {
            String[] splitCommand = userCommand.split(" ", 2);
            tasks.add(new Todo(splitCommand[1]));
            ++taskCounter;
            printDetails(tasks);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You have not given any description.");
        }
    }

    public static void addDeadline(ArrayList<Task> tasks){
        String[] splitCommand = userCommand.split(" ", 2);
        String[] splitCommandAgain = splitCommand[1].split(" /by ");
        tasks.add(new Deadlines(splitCommandAgain[0], splitCommandAgain[1]));
        ++taskCounter;
        printDetails(tasks);
    }

    public static void addEvent(ArrayList<Task> tasks){
        String[] splitCommand = userCommand.split(" ", 2);
        String[] splitCommandAgain = splitCommand[1].split(" /at ");
        tasks.add(new Events(splitCommandAgain[0], splitCommandAgain[1]));
        ++taskCounter;
        printDetails(tasks);
    }

    public static void printDetails(ArrayList<Task> tasks){
        System.out.println("Got it. I've added this task: ");
        System.out.println("        " + taskCounter + ". " + tasks.get(taskCounter - 1).toString());
        System.out.println("Now you have "+taskCounter+ " tasks in the list.");
    }


    public static void printHorizontal(){
        String horizontalLine = "------------------------------------------";
        System.out.println(horizontalLine);
    }
}
