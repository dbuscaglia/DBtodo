package com.codepath.danbuscaglia.dbtodo.controllers;

import com.codepath.danbuscaglia.dbtodo.models.TodoItemTask;

import java.util.List;

/**
 * Interface for operations to store Todos
 *
 * Follows a CUDA pattern (create, update, delete, all)
 * TODO: implement deleteAll()
 */
public interface TodoController {

    /**
     * Fetch all the Todos in the data store
     * @return List of Todos
     */
    List<TodoItemTask> all();

}
