package com.codepath.danbuscaglia.dbtodo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "tbl_todo_task")
public class TodoItemTask extends Model implements Serializable {

    @Column(name = "Task")
    public String name;

    @Column(name = "Priority", index = true)
    public PriorityLevel priority;

    @Column(name = "TaskDate")
    public Date task_date;

    @Column(name = "Finished")
    public boolean finished;

    public TodoItemTask() {
        super();
    }

    public TodoItemTask(String name, PriorityLevel priority){
        super();
        this.name = name;
        this.priority = priority;
        this.finished = false;
        this.task_date = new Date();
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Get the priority of the given TodoItemTask
     * @return PriorityLevel
     */
    public PriorityLevel getPriority() {
        return this.priority;
    }
}