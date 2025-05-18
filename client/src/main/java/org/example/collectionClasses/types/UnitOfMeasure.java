package org.example.collectionClasses.types;

import org.example.readers.UnitOfMeasureReadable;

import java.io.Serializable;

/**
 * Possible units of measurement.
 */
public enum UnitOfMeasure implements Serializable {
    KILOGRAMS("kilograms"),
    CENTIMETERS("centimeters"),
    SQUARE_METERS("square_meters"),
    PCS("pcs");

    private String name;

    UnitOfMeasure(String name) {
        this.name = name;
    }

    public static void printAll(){
        for (UnitOfMeasure cur: UnitOfMeasure.values()){
            System.out.println(cur.toString());
        }
    }

    /**
     * Maps the string name to a UnitOfMeasure object.
     * @param name
     * @return a UnitOfMeasure object.
     */
    public static UnitOfMeasure fromString(String name) {
        for (UnitOfMeasure elem : UnitOfMeasure.values()) {
            if (name.equalsIgnoreCase(elem.toString())) {
                return elem;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}