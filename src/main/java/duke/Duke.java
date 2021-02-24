package duke;
import java.util.Scanner;


public class Duke {
    static final int MAX_TASK = 100;
    static String userCommand;
    static int taskCounter=0;

    static Task[] tasks = new Task[MAX_TASK];

    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        printHorizontal();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printHorizontal();

        userCommand = (input.nextLine()).trim();

        while(!(userCommand.equals("bye"))) {
            printHorizontal();
            if(userCommand.equals("list")) {
                listTasks(tasks);
            } else if(userCommand.contains("done ")) {
                markTasks(tasks);
            } else if(userCommand.contains("todo ")) {
                addTodo(tasks);
            } else if(userCommand.contains("deadline ")){
                addDeadline(tasks);
            } else if(userCommand.contains("event ")) {
                addEvent(tasks);
            } else {
                System.out.println("Invalid input!");
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

    public static void markTasks(Task[] tasks){

        int taskIndex;
        String taskNumAsString=userCommand.substring(5);
        int taskNumAsInt = Integer.parseInt(taskNumAsString);

        if(!(taskNumAsInt>0 && taskNumAsInt<=taskCounter)){
            System.out.println("Sorry! There are no such tasks!");
        } else {
            taskIndex=taskNumAsInt-1;
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:"+System.lineSeparator()+
                    "        " + taskNumAsInt + ". " + tasks[taskIndex].toString());
        }
    }

    public static void addTodo(Task[] tasks){
        String[] splitCommand = userCommand.split(" ", 2);
        tasks[taskCounter++] = new Todo(splitCommand[1]);
        printDetails(tasks);
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
