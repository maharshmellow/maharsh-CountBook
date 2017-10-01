package net.maharsh.maharsh_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Counter and stores information related to an individual Counter object
 */

public class Counter {
    private String name;
    private String dateModified;
    private int initialValue;
    private int currentValue;
    private String comment;

    /**
     * Creates a new Counter
     * @param name Name of the counter
     * @param initialValue Initial Value of the counter
     * @param comment Optional Comment for the counter
     */
    public Counter(String name, int initialValue, String comment){
        this.name = name;
        this.dateModified = new SimpleDateFormat("yyyy-MM-dd").format(new Date());;
        this.initialValue = initialValue;
        this.currentValue = initialValue;   // initially the current value is the initial value
        this.comment = comment;
    }

    public void increment(){
        this.currentValue += 1;
    }

    /**
     * Decrements the counter as long as it doesn't go below 0
     */
    public void decrement(){
        if (this.currentValue > 0){
            this.currentValue -= 1;
        }
    }

    public void reset(){
        this.currentValue = this.initialValue;
    }

    public String getName(){
        return this.name;
    }

    public int getCurrentValue(){
        return this.currentValue;
    }

    public int getInitialValue(){
        return this.initialValue;
    }

    public String getComment(){
        return this.comment;
    }

    public String getDateModified(){
        return this.dateModified;
    }

    /**
     * Edits the counter data
     * Called when the person presses the edit button and makes changes
     * @param name
     * @param initialValue
     * @param currentValue
     * @param comment
     */
    public void editCounter(String name, int initialValue, int currentValue, String comment){
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
        this.comment = comment;
        this.dateModified = new SimpleDateFormat("yyyy-MM-dd").format(new Date());;
    }

}
