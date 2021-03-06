# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update IntelliJ to the most recent version.

### On IntelliJ version 2020.3.1 (latest) and beyond

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Import the project into IntelliJ as follows:
   1. Click `Open`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()`. If the setup is correct, you should see something like the output below.

```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   
------------------------------------------
	Hello! I'm Duke
	What can I do for you?
------------------------------------------ 
```

## Running the JAR File on computer:
The **Duke** app is run via a file with the extension `.jar`. The following steps will help you set up **Duke** to run on your computer.

1. Set up the `.jar` file by downloading the latest version from [here](https://github.com/nivikcivik/ip/releases)
    1. Click on `Individual Project.jar` under the latest version (`v1.0` or beyond)
    1. Once the download prompt appears, `save` the file to your desired folder on your computer. This folder will be the _home folder_ for your tasks list.
 
1. Look for the `terminal` or `command prompt` application on your computer.
    1. Type `java --version` in the terminal on your computer to check the version of java and ensure its JDK 11.
    2. Locate your _home folder_ where you have saved the `.jar` file.
    3. Use commands `cd` to change directory ( or folder) and `ls` (or `dir` on command prompt on Windows) to view the files in your current directory.
 
1. Once you have entered the _home folder_, type the following command: `java -jar <name of .jar file>` e.g. `java -jar ip.jar`
1. You should see the **Duke** logo along with a welcome message.   
   **Duke** is now running!
    1. You would see this greeting message, for example:

```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   
------------------------------------------
	Hello! I'm Duke
	What can I do for you?
------------------------------------------ 
```

 
## Command Summary:

1.`list` : Lists all the tasks that you have created.
1. `done taskNumber` : Marks a particular task as done.
    For example: `>done 1` in this following example will give:
    ```
    Here are the tasks in your list:
	1. [T][ ] read
	2. [D][ ] kk (by: Mar 08 2021, 04:30 PM)
	3. [E][ ] meeting (at: 2021-08-19 2:00)
	
	> done 2
	
   ------------------------------------------
   Nice! I've marked this task as done:
	2. [D][✓] kk (by: Mar 08 2021, 04:30 PM)
	------------------------------------------
   ```
1.  `todo description` : Create/adds a Todo task with a particular description. For ex: `> todo read books;`
1. `deadline description /by YYYY-MM-DD HH:MM` : Create/adds a Deadline task with a particular description and deadline written in the below format:
      Date: YYYY-MM-DD. Ex: 2021-03-09
      Time: HH:MM (24-hr format). Ex: 16:00
      `> deadline assignment /by 2021-03-09 16:00`
      ```
      Got it. I've added this task: 
        2. [D][ ] assignment (by: Mar 09 2021, 04:00 PM)
           Now you have 2 tasks in the list.
      ```
1. `event description /at YYYY-MM-DD HH:MM` : Create/adds an Event task with a particular description and event time written in the below format:
      Date: YYYY-MM-DD. Ex: 2021-03-09
      Time: HH:MM (24-hr format). Ex: 16:00
      `> event meeting /at 2021-03-09 16:00`
      ```
      Got it. I've added this task: 
        3. [E][ ] meeting (at: Mar 09 2021, 04:00 PM)
           Now you have 3 tasks in the list.
      ```
1. `find keyword` : Searched for the keyword among the tasks and lists down all tasks which contain it.
    For Ex: `> find read`
    ```
    Searching for task with keyword: read...
	1. [T][ ] read
	5. [T][ ] read books

    ```
    NOTE: Keywords must only be **SINGLE-WORDED** with **NO TRAILING WHITE SPACES*.
    
1. `delete taskNumber`: Removes a specified task. For Ex: `> delete 3`
1. `bye`: Terminates the program, exits from application. 

NOTE: To safely save your data of the tasks, remember to say `bye`.



   




