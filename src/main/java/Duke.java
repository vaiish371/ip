import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n\n");

        int taskCounter = 0;
        String line;
        String[] tasks = new String[100];
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while(line.equals("bye") == false)
        {
            if(line.equals("list")==true)
            {
                for(int j=0;j<taskCounter;++j)
                {
                    System.out.println("        "+ (j+1)+". "+tasks[j]);
                }
            }
            else
            {
                tasks[taskCounter++]= line;
                System.out.println("        added: " + line);
            }
            line = in.nextLine();
        }

        System.out.print("      Bye. Hope to see you again soon!");
    }
}
