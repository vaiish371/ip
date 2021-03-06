package duke;

import java.util.Scanner;

public class Ui {

    public Ui(){

    }
    public void printHelloMsg(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printHorizontal();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printHorizontal();
    }

    public void printByeMsg(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void printHorizontal() {
        String horizontalLine = "------------------------------------------";
        System.out.println(horizontalLine);
    }

    public void showLoadingError(){
        System.out.println("There seems to be some I/O error. Exiting...");
    }

    public String readCommand(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
