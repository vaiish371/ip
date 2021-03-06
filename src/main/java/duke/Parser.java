package duke;

import duke.taskclass.Task;

import java.util.ArrayList;

public class Parser {

    protected ArrayList<Task> taskArray;
    protected Ui ui;

    public Parser(ArrayList<Task> tasks) {
        this.taskArray = tasks;
    }

    public void parseCommand(String userCommand) throws DukeException {
        TaskList taskList = new TaskList(taskArray);
        //ui.printHorizontal();
        try {
            if (userCommand.trim().equals("list")) {
                taskList.listTasks();
            } else if (userCommand.contains("done")) {
                String taskNumAsString = userCommand.substring(5);
                int taskNumAsInt = Integer.parseInt(taskNumAsString);
                taskList.markTasks(taskNumAsInt-1);
            } else if (userCommand.contains("todo")) {
                String[] splitCommand = userCommand.split(" ", 2);
                taskList.addTodo(splitCommand[1]) ;
            } else if (userCommand.contains("deadline")) {
                String[] splitCommand = userCommand.split(" ", 2);
                String[] splitCommandAgain = splitCommand[1].split(" /by ");
                taskList.addDeadline(splitCommandAgain[0], splitCommandAgain[1]);
            } else if(userCommand.contains("event")) {
                String[] splitCommand = userCommand.split(" ", 2);
                String[] splitCommandAgain = splitCommand[1].split(" /at ");
                taskList.addEvent(splitCommandAgain[0], splitCommandAgain[1]);
            } else if (userCommand.contains("delete")) {
                String[] splitCommand = userCommand.split(" ", 2);
                int taskNumAsInt = Integer.parseInt(splitCommand[1]);
                taskList.deleteTasks(taskNumAsInt - 1);
            } else {
                throw new DukeException();
            }

        } catch (NumberFormatException e) {
            System.out.println("Task is not specified as a number.");
        } catch (NullPointerException|DukeArrayException e) {
            System.out.println("There are no such tasks added yet.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task Number/Description is either empty or out of bounds.");
        }
        //ui.printHorizontal();
    }
}
