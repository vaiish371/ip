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

    //This function marks the tasks as done.
    public void markTasks(int index) throws DukeArrayException{
        if(index>tasks.size() || index<0) {
            throw new DukeArrayException();
        }
        tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    "\t" + (index+1) + ". " + tasks.get(index).toString());

    }

    public void deleteTasks(int index) throws DukeArrayException{
        if(index>tasks.size() || index<0) {
            throw new DukeArrayException();
        }
            System.out.println("Noted! I've removed this task:" + System.lineSeparator() +
                    "\t" + (index+1) + ". " + tasks.get(index).toString());
            tasks.remove(index);
            System.out.println("Now you have "+tasks.size()+" tasks in your list.");
    }

    public void addTodo(String description){
            tasks.add(new Todo(description));
            printDetails();
    }

    public void addDeadline(String description, String by){
        String formattedBy = formatDateTime(by);
        tasks.add(new Deadlines(description,formattedBy));
        printDetails();
    }

    public void addEvent(String description, String at){
        String formattedAt = formatDateTime(at);
        tasks.add(new Events(description,formattedAt));
        printDetails();
    }

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

    public void printDetails(){
        System.out.println("Got it. I've added this task: ");
        System.out.println("        " + tasks.size() + ". " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have "+tasks.size()+ " tasks in the list.");
    }

}
