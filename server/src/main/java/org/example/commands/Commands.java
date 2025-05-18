package org.example.commands;

import java.io.Serializable;

public enum Commands implements Serializable {
    HELP("help"),
    INFO("info"),
    SHOW("show"),
    INSERT("insert"),
    SAVE("save"),
    UPDATE("update"),
    REMOVE_KEY("remove_key"),
    CLEAR("clear"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    REPLACE_IF_GREATER("replace_if_greater"),
    REPLACE_IF_LOWER("replace_if_lower"),
    REMOVE_GREATER_KEY("remove_greater_key"),
    MIN_BY_MANUFACTURER("min_by_manufacturer"),
    MAX_BY_COORDINATES("max_by_coordinates"),
    PRINT_FIELD_ASCENDING_PRICE("print_field_ascending_price"),
    REPEAT_LAST_COMMAND("repeat last command");

    private String text;

    private Commands(String text){
        this.text = text;
    }

    public static Commands fromString(String command){
        for (Commands current: Commands.values()){
            if (current.toString().equals(command)){
                return current;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.text;
    }
}