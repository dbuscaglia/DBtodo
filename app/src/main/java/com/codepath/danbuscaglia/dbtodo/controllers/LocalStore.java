package com.codepath.danbuscaglia.dbtodo.controllers;

import com.activeandroid.query.Select;
import com.codepath.danbuscaglia.dbtodo.models.TodoItemTask;

import java.util.List;

/**
 * Sqllite controller using android active ORM
 */
public class LocalStore implements TodoController {

    private static final LocalStore _instance = new LocalStore();

    public static LocalStore db() {
        return _instance;
    }

    @Override
    public List<TodoItemTask> all() {
        return new Select().from(TodoItemTask.class)
                .orderBy("Priority ASC").limit(100).execute();
    }
}
