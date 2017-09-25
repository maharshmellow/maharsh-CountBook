package net.maharsh.maharsh_countbook;

import java.util.Date;

/**
 * Created by maharshmellow on 2017-09-24.
 */

public class Counter {
    private String name;
    private Date dateCreated;
    private int initialValue;
    private int currentValue;
    private String comment;

    public Counter(String name, int initialValue, String comment){
        // creates a new counter
        this.name = name;
        this.dateCreated = new Date();
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment = comment;
    }

    public void increment(){
        this.currentValue += 1;
    }

    public void decrement(){
        this.currentValue -= 1;
    }

    public void reset(){
        this.currentValue = this.initialValue;
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

    public Date getDateCreated(){
        return this.dateCreated;
    }

    public void editCounter(String name, int initialValue, int currentValue, String comment){
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
        this.comment = comment;
    }
    @Override
    public String toString(){
        // this value will be used by the array list to display the counter name in the ListView
        return this.name;
    }
}
