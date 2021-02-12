public class Task   {
        protected String description;
        protected boolean isDone;


        public Task(String description) {
            this.description= description;
            this.isDone = false;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getTaskType(){
            return (" ");
        }

        public void markAsDone() {
            isDone = true;
        }

        @Override
        public String toString(){
            return ("[" + getStatusIcon() + "] "+ description);
        }

    }