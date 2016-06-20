package com.example.lucerne.todo;

import java.io.Serializable;

/**
 * Created by lucerne on 6/19/16.
 */
//public class TodoTask implements Serializable {
//  public static final long serialVersionUID = 5177222050535318633L;
public class TodoTask {
    public String name;
    public String priority;
    public String notes;
    public String status;
    public int dueDate;

    public TodoTask(String d, String a, String b, String c, int i) {
        name = d;
        priority = a;
        notes = b;
        status = c;
        dueDate = i;
    }
}