package com.example.lucerne.todo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by lucerne on 6/19/16.
 */

@Table(name = "Priorities")
public class Priority extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true)
    public long remoteId;
    // This is a regular field
    @Column(name = "Name")
    public String name;

    // Make sure to have a default constructor for every ActiveAndroid model
    public Priority(){
        super();
    }

    // Used to return items from another table based on the foreign key
    public List<TodoItem> items() {
        return getMany(TodoItem.class, "Priority");
    }
}