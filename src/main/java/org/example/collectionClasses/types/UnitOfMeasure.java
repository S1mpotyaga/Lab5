package org.example.collectionClasses.types;

import org.example.collectionClasses.readers.UnitOfMeasureReadable;

import jakarta.xml.bind.annotation.*;

/**
 * Possible units of measurement.
 */
@XmlEnum(String.class)
public enum UnitOfMeasure implements UnitOfMeasureReadable {
    @XmlEnumValue("kilograms")
    KILOGRAMS("kilograms"),
    @XmlEnumValue("centimeters")
    CENTIMETERS("centimeters"),
    @XmlEnumValue("square_meters")
    SQUARE_METERS("square_meters"),
    @XmlEnumValue("pcs")
    PCS("pcs");

    private String name;

    private UnitOfMeasure(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
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

    public static void printAll(){
        for (UnitOfMeasure current: UnitOfMeasure.values()){
            System.out.println(current.toString().toUpperCase());
        }
    }
}