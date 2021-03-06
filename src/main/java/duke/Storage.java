package duke;

import duke.taskclass.Deadlines;
import duke.taskclass.Events;
import duke.taskclass.Task;
import duke.taskclass.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public Storage() {

    }

    /**
     *Loads the data of Tasks from hard disk when Duke starts up.
     *
     * @param filePath File path of the text file from which we load any existing tasks from.
     * @return Task array with the file contents which is later used to instantiate TaskList.
     * @throws FileNotFoundException when specified file is not found in the file path.
     */
    public  ArrayList<Task> loadTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner fileContent = new Scanner(f);
        ArrayList<Task> tempArray = new ArrayList<>();
        while (fileContent.hasNext()) {
            String fileInput = fileContent.nextLine();
            String[] splitLine = fileInput.split(" : ");
            switch (splitLine[0].trim()) {
            case "T":
                tempArray.add(new Todo(splitLine[2]));
                break;
            case "E":
                tempArray.add(new Events(splitLine[2], splitLine[3]));
                break;
            case "D":
                tempArray.add(new Deadlines(splitLine[2], splitLine[3]));
                break;
            default:
                System.out.println("Task is not valid");
            }
            if (splitLine[1].equals("✓")) {
                tempArray.get(tempArray.size() - 1).markAsDone();
            }
        }
        return tempArray;
    }

    /**
     *Saves the tasks in the hard disk automatically whenever the task list changes by
     * writing to the text file specified
     *
     * @param tasks tasks Array that contains the task list and contents
     * @throws IOException when input or output is implemented incorrectly.
     */
    public void saveTasks(ArrayList<Task> tasks) {

        String filePath = new File("").getAbsolutePath();
        try {
            FileWriter fw = new FileWriter(filePath + "/data.txt");
            for (Task taskObj : tasks) {
                if (taskObj instanceof Todo) {
                    if (taskObj.isDone) {
                        fw.write("T : ✓ : " + taskObj.getDescription() + System.lineSeparator());
                    } else {
                        fw.write("T : X : " + taskObj.getDescription() + System.lineSeparator());
                    }
                } else if (taskObj instanceof Deadlines) {
                    if (taskObj.isDone) {
                        fw.write("D : ✓ : ");
                    } else {
                        fw.write("D : X : ");
                    }
                    fw.write(taskObj.getDescription() + " : " + ((Deadlines) taskObj).getBy() + System.lineSeparator());
                } else if (taskObj instanceof Events) {
                    if (taskObj.isDone) {
                        fw.write("E : ✓ : ");
                    } else {
                        fw.write("E : X : ");
                    }
                    fw.write(taskObj.getDescription() + " : " + ((Events) taskObj).getEventTime() + System.lineSeparator());
                } else {
                    System.out.println("Class not found!");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("ERROR: something went wrong! :(");
        }
    }

}
