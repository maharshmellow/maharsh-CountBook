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

    public void updateCounter(int incrementValue){
        // increments the counter (decrements if incrementValue < 0)
        this.currentValue += incrementValue;

    }
}
