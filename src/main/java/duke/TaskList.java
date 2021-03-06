package duke;

import duke.taskclass.Deadlines;
import duke.taskclass.Events;
import duke.taskclass.Task;
import duke.taskclass.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 *
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> taskArray){
        tasks = taskArray;
    }

    public void listTasks(){
        if(tasks.size()==0) {
            System.out.println("No tasks are there.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task taskObject: tasks ) {
                System.out.println("\t" + (tasks.indexOf(taskObject) + 1) + ". " + taskObject.toString());
            }
        }
    }

    /**
     * Marks a specified task as done[✓] and displays its details.
     *
     * @param index Array Index of a Task.
     * @throws DukeArrayException if index is out of bounds.
     */
    public void markTasks(int index) throws DukeArrayException{
        if(index>tasks.size() || index<0) {
            throw new DukeArrayException();
        }
        tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    "\t" + (index+1) + ". " + tasks.get(index).toString());

    }

    /**
     * Removes a specified task from tasks Array and displays its details,
     * along with the number of existing tasks.
     *
     * @param index Array Index of a Task.
     * @throws DukeArrayException if index is out of bounds.
     */
    public void deleteTasks(int index) throws DukeArrayException{
        if(index>tasks.size() || index<0) {
            throw new DukeArrayException();
        }
            System.out.println("Noted! I've removed this task:" + System.lineSeparator() +
                    "\t" + (index+1) + ". " + tasks.get(index).toString());
            tasks.remove(index);
            System.out.println("Now you have "+tasks.size()+" tasks in your list.");
    }

    /**
     * Adds Todo task.
     *
     * @param description Description of the todo task.
     */
    public void addTodo(String description){
            tasks.add(new Todo(description));
            printDetails();
    }

    /**
     * Adds Deadline task and formats the deadline date and time.
     *
     * @param description Description of the deadline task.
     * @param by Specifies the date and time of deadline.
     */
    public void addDeadline(String description, String by){
        String formattedBy = formatDateTime(by);
        tasks.add(new Deadlines(description,formattedBy));
        printDetails();
    }

    /**
     * Adds Event task and formats the event date and time.
     *
     * @param description Description of the deadline task.
     * @param at Specifies the date and time of the event.
     */
    public void addEvent(String description, String at){
        String formattedAt = formatDateTime(at);
        tasks.add(new Events(description,formattedAt));
        printDetails();
    }

    /**
     * Finds and displays all the tasks that contain user's keyword.
     *
     * @param keyword Keyword entered by user to find the corresponding tasks.
     * @throws MultipleKeywordException when user does not enter single-worded keyword.
     */
    public void findTask(String keyword) throws MultipleKeywordException{
        if(keyword.contains(" ")){
           throw new MultipleKeywordException();
        }
        int flag = 0;
        System.out.println("Searching for task with keyword: "+keyword+"...");
        for(Task taskObj: tasks){
            if(taskObj.getDescription().contains(keyword)){
                System.out.println("\t" + (tasks.indexOf(taskObj)+1) + ". "+taskObj.toString());
                flag = 1;
            }
        }
        if(flag==0){
            System.out.println("Sorry :(, can't find task with keyword: "+keyword);
        }
    }

    /**
     *Formats the date and time entered by user into a more readable form.
     *
     * @param dateCommand Contains the date and time entered by user,
     *                    in the "YYYY-MM-DD HH:MM" format.
     * @return the formatted date and time in "MMM dd yyyy hh:mm a" format.
     */
    public static String formatDateTime(String dateCommand) {
        String[] splitCommand = dateCommand.split(" ", 2);
        try {
            LocalDate taskDate = LocalDate.parse(splitCommand[0]);
            String formattedDate = taskDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

            LocalTime taskTime = LocalTime.parse(splitCommand[1]);
            String formattedTime = taskTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

            return (formattedDate + ", " + formattedTime);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException dpe) {
            return dateCommand;
        }
    }

    /**
     * Prints details of the task that is added and
     * displays total number of existing tasks.
     */
    public void printDetails(){
        System.out.println("Got it. I've added this task: ");
        System.out.println("        " + tasks.size() + ". " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have "+tasks.size()+ " tasks in the list.");
    }

}
