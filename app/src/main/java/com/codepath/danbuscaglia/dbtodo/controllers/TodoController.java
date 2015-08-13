package com.codepath.danbuscaglia.dbtodo.controllers;

import com.codepath.danbuscaglia.dbtodo.models.Todo;

import java.util.List;

/**
 * Interface for operations to store Todos
 *
 * Follows a CUDA pattern (create, update, delete, all)
 * TODO: implement deleteAll()
 */
public interface TodoController {

    /**
     * Create a Todo
     * @param todo
     * @return id of Todo
     */
    long create (Todo todo);

    /**
     * Updates a Todo
     * @param todo
     * @return id of Todo
     */
    long update (Todo todo);

    /**
     * Remove a Todo
     * @param todo
     * @return if the Todo was deleted
     */
    void delete (Todo todo);

    /**
     * Fetch all the Todos in the data store
     * @return List of Todos
     */
    List<Todo> all();

}
