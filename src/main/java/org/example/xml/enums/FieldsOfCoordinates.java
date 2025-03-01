package org.example.xml.enums;

import java.util.ArrayList;

public enum FieldsOfCoordinates{
    X("x"),
    Y("y");

    private String text;

    private FieldsOfCoordinates(String text){
        this.text = text;
    }

    public static ArrayList<String> stringValues(){
        ArrayList<String> result = new ArrayList<>();
        for (FieldsOfCoordinates elem: FieldsOfCoordinates.values()){
            result.add(elem.toString());
        }
        return result;
    }

    @Override
    public String toString(){
        return this.text;
    }
}