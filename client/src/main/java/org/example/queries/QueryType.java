package org.example.queries;

import java.io.Serializable;

public enum QueryType implements Serializable {
    GET_ID("get id"),
    GET_PRODUCT("get product"),
    ALL_DONE("all done"),
    EXIT("exit"),
    GET_FILE_NAME("get file name"),
    ERROR("error"),
    NOT_FOUND_OBJECT("not found object"),
    REPEAT_LAST_COMMAND("repeat last command"),
    WRONG_COMMAND("wrong command"),
    WRONG_DATA("wrong data"),
    FINISH_READING_FILE("finish reading file");

    private String text;

    QueryType(String text){
        this.text = text;
    }

    public static QueryType fromString(String name){
        for (QueryType current: QueryType.values()){
            if (name.equals(current.toString())){
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