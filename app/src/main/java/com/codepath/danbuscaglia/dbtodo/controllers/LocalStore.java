package com.codepath.danbuscaglia.dbtodo.controllers;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.codepath.danbuscaglia.dbtodo.models.Todo;

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
    public long create(Todo todo) {
        return todo.save();
    }

    @Override
    public void delete(Todo todo) {
        todo.delete();
    }

    @Override
    public long update(Todo todo) {
        return todo.save();
    }

    @Override
    public List<Todo> all() {
        return new Select().from(Todo.class)
                .orderBy("ID ASC").limit(100).execute();
    }
}
