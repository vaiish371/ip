import java.util.Scanner;


public class Duke {
    static String userCommand;
    static int taskCounter=0;

    public static final int MAX_TASK = 100;

    static Task[] tasks = new Task[MAX_TASK];

    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        printHorizontal();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printHorizontal();

        userCommand = input.nextLine();

        while(!(userCommand.equals("bye"))) {
            if(userCommand.equals("list")) {
               listTasks(tasks);
            } else if(userCommand.contains("done ")) {
                markTasks(tasks,userCommand.trim());
            } else {
                tasks[taskCounter++] = new Task(userCommand);
                printHorizontal();
                System.out.println("Added: "+ userCommand);
                printHorizontal();
            }
            userCommand = input.nextLine();
        }

        printHorizontal();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public static void listTasks(Task[] tasks){
        printHorizontal();
        if(taskCounter==0) {
            System.out.println("No tasks are there.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int j = 0; j < taskCounter; ++j) {
                System.out.println("        " + (j + 1) + ".[" + tasks[j].getStatusIcon() + "] " +
                        tasks[j].getDescription());
            }
        }
        printHorizontal();
    }

    public static void markTasks(Task[] tasks,String userInput){

        int taskIndex;
        String taskNumAsString=userInput.substring(5);
        int taskNumAsInt = Integer.parseInt(taskNumAsString);

        printHorizontal();
        if(!(taskNumAsInt>0 && taskNumAsInt<=taskCounter)){
            System.out.println("Sorry! There are no such tasks!");
        } else {
            taskIndex=taskNumAsInt-1;
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:"+System.lineSeparator()+
                    "["+tasks[taskIndex].getStatusIcon()+"] "+tasks[taskIndex].getDescription());
        }
        printHorizontal();
    }

    public static void printHorizontal(){
        String horizontalLine = "------------------------------------------";
        System.out.println(horizontalLine);
    }
}
