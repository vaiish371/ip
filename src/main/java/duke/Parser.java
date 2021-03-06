package duke;

import duke.taskclass.Task;

import java.util.ArrayList;

public class Parser {

    protected ArrayList<Task> taskArray;

    public Parser(ArrayList<Task> tasks) {
        this.taskArray = tasks;
    }

    /**
     * Determines the commands entered by user and
     * the appropriate function to call.
     *
     * @param userCommand Command typed in by user
     * @throws DukeException when there is an invalid command send in by user.
     */
    public void parseCommand(String userCommand) throws DukeException {
        TaskList taskList = new TaskList(taskArray);
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
            } else if(userCommand.contains("find")){
                String[] splitCommand = userCommand.split(" ", 2);
                taskList.findTask(splitCommand[1]);
            } else if (userCommand.contains("delete")) {
                String[] splitCommand = userCommand.split(" ", 2);
                int taskNumAsInt = Integer.parseInt(splitCommand[1]);
                taskList.deleteTasks(taskNumAsInt - 1);
            } else {
                throw new DukeException();
            }

        } catch (NumberFormatException e) {
            //When task numbers are not numeral. For example, "done two", instead of "done 2"
            System.out.println("Task is not specified as a number.");
        } catch (NullPointerException|DukeArrayException e) {
            /**
             *When user tries to delete/mark done, tasks that are beyond
             *the current size of the task array.
             */
            System.out.println("There are no such tasks added yet.");
        } catch (ArrayIndexOutOfBoundsException e) {
            //When user does not specify task number/description or the number is out of bounds.
            System.out.println("Task Number/Description is either empty or out of bounds.");
        } catch (MultipleKeywordException e) {
            //When user enter multiple keywords instead of single-worded keyword.
            System.out.println("No multiple keywords allowed! Try single keyword or " +
                    "omitting trailing whitespaces.");
        }
    }
}
