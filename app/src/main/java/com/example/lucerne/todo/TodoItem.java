package com.example.lucerne.todo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "TodoItems")
public class TodoItem extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;
    // This is a regular field
    @Column(name = "Name")
    public String name;
    // This is an association to another activeandroid model
    @Column(name = "Priority", onUpdate = Column.ForeignKeyAction.CASCADE,
            onDelete = Column.ForeignKeyAction.CASCADE)
    public Priority priority;

    // Make sure to have a default constructor for every ActiveAndroid model
    public TodoItem(){
        super();
    }

    public TodoItem(int remoteId, String name, Priority priority){
        super();
        this.remoteId = remoteId;
        this.name = name;
        this.priority = priority;
    }

    public static List<TodoItem> getAll(Priority priority) {
        // This is how you execute a query
        return new Select()
                .from(TodoItem.class)
                .where("Priority = ?", priority.getId())
                .orderBy("Name ASC")
                .execute();
    }

}