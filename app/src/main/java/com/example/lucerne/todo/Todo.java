package com.example.lucerne.todo;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by lucerne on 6/27/16.
 */

public class Todo implements Serializable {

    private String name;
    private String priority;
    private String notes;
    private String status;
    private String dueDate;

    public Todo(String name, String priority, String notes, String status,
                String dueDate) {
        this.name = name;
        this.priority = priority;
        this.notes = notes;
        this.status = status;
        this.dueDate = dueDate;
    }

    public String getName() { return this.name; }
    public String getPriority() { return this.priority; }
    public String getNotes() { return this.notes; }
    public String getStatus() { return this.status; }
    public String getDueDate() { return this.dueDate;}

    public void setName(String name) { this.name = name; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setStatus(String status) { this.status = status; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate;}


    @Override
    public String toString() {
        return this.name + " " + this.priority + " " + this.notes + " " +
                this.status + " " + this.dueDate;
    }
}