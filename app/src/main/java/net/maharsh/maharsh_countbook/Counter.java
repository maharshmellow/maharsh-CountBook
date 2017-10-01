package net.maharsh.maharsh_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maharshmellow on 2017-09-24.
 */

public class Counter {
    private String name;
    private String dateModified;
    private int initialValue;
    private int currentValue;
    private String comment;

    public Counter(String name, int initialValue, String comment){
        // creates a new counter
        this.name = name;
        this.dateModified = new SimpleDateFormat("yyyy-MM-dd").format(new Date());;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment = comment;
        System.out.println(this.dateModified);
    }

    public void increment(){
        this.currentValue += 1;
    }

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


    public void editCounter(String name, int initialValue, int currentValue, String comment){
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
        this.comment = comment;
        this.dateModified = new SimpleDateFormat("yyyy-MM-dd").format(new Date());;
    }
    @Override
    public String toString(){
        // this value will be used by the arrayadapter to display the counter name in the ListView
        return this.name;
    }
}
