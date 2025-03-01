package org.example.xml.enums;

import java.util.ArrayList;

public enum FieldsOfProduct {
    PRODUCT("product"),
    ID("id"),
    NAME("name"),
    COORDINATES("coordinates"),
    PRICE("price"),
    UNIT_OF_MEASURE("unitOfMeasure"),
    ORGANIZATION("organization");

    private String text;

    private FieldsOfProduct(String text){
        this.text = text;
    }

    public static ArrayList<String> stringValues(){
        ArrayList<String> result = new ArrayList<>();
        for (FieldsOfProduct elem: FieldsOfProduct.values()){
            result.add(elem.toString());
        }
        return result;
    }

    @Override
    public String toString(){
        return this.text;
    }
}
