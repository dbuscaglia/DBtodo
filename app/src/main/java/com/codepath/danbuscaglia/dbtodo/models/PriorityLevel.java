package com.codepath.danbuscaglia.dbtodo.models;

public enum PriorityLevel {
    /**
     * This enum is used to set a priority level for any valid task
     *
     * The use case for this ENUM is to provide priority types to easily
     * add into the Priority table and associate with an icon / display
     *
     *
     */
    HIGHEST("!", 0),
    HIGH("*", 1),
    MEDIUM("+", 2),
    LOW("-", 3),
    REMINDER("_", 4);

    private String priority_name;
    private int priority_level;

    PriorityLevel(String priority_name, int value) {
        this.priority_name = priority_name;
    }

    @Override
    public String toString() {
        return priority_name;
    }

    public int value() {
        return priority_level;
    }
}
