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
    HIGHEST("Highest", 0),
    HIGH("High", 1),
    MEDIUM("Medium", 2),
    LOW("Low", 3),
    REMINDER("Reminder", 4);

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
