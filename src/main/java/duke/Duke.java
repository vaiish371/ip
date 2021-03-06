package duke;

import duke.taskclass.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    public static ArrayList<Task> taskArray = new ArrayList<>();
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            taskArray = storage.loadTasks(filePath + "/data.txt");
            tasks = new TaskList(taskArray);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Try again.");
        }
    }

    public void run() {
        ui.printHelloMsg();
        boolean isExit = false;
        while (true) {
            try {
                String fullCommand = ui.readCommand().trim();
                if(fullCommand.equals("bye")){
                    break;
                }
                ui.printHorizontal(); // show the divider line ("_______")
                parser = new Parser(taskArray);
                parser.parseCommand(fullCommand);
                storage.saveTasks(taskArray);
            } catch (DukeException e){
                ui.printInvalidCommand();
            }
        }
        storage.saveTasks(taskArray);
        ui.printByeMsg();
    }

    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        new Duke(filePath).run();
    }
}
