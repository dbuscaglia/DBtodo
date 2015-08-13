package com.codepath.danbuscaglia.dbtodo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.List;

@Table(name = "Todos")
public class Todo extends Model {

    @Column(name = "Task")
    public String name;

    @Column(name = "Priority", index = true)
    public int priority;

    public Todo() {
        super();
    }

    public Todo(String name, PriorityLevel priority){
        super();
        this.name = name;
        this.priority = priority.value();
    }

    @Override
    public String toString() {
        return name;
    }
}
