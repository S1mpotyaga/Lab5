package org.example.xml.enums;

import java.util.ArrayList;

public enum FieldsOfOrganization {
    ID("id"),
    NAME("name"),
    FULL_NAME("fullName"),
    TYPE_ORGANIZATION("typeOfOrganization");

    private String text;

    private FieldsOfOrganization(String text){
        this.text = text;
    }

    public static ArrayList<String> stringValues(){
        ArrayList<String> result = new ArrayList<>();
        for (FieldsOfOrganization elem: FieldsOfOrganization.values()){
            result.add(elem.toString());
        }
        return result;
    }

    @Override
    public String toString(){
        return this.text;
    }
}
