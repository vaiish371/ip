package duke;

public class Events extends Task{

    protected String eventTime;

    public Events(String description, String eventTime){
        super(description);
        this.eventTime = eventTime;
    }

    public String getEventTime(){
        return eventTime;
    }

    @Override
    public String toString(){
        return ("[E]" + super.toString() + " (at: " + eventTime + ")");
    }
}
