import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n\n");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while(line.equals("bye") == false)
        {
            System.out.println("        " + line);
            line = in.nextLine();
        }

        System.out.print("      Bye. Hope to see you again soon!");
    }
}
