package org.example.collectionClasses.enums;

import java.util.ArrayList;

public enum UnitOfMeasure{
    KILOGRAMS("kilograms"),
    CENTIMETERS("centimeters"),
    SQUARE_METERS("square_meters"),
    PCS("pcs");

    private  String name;

    private UnitOfMeasure(String name){
        this.name = name;
    }



    @Override
    public String toString(){
        return this.name;
    }

    public static UnitOfMeasure fromString(String tmp){
        for (UnitOfMeasure unitOfMeasure: UnitOfMeasure.values()){
            if (unitOfMeasure.name.equalsIgnoreCase(tmp)){
                return unitOfMeasure;
            }
        }
        return null;
    }
}