package org.example.collectionClasses.types;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

import java.io.Serializable;

/**
 * Possible units of measurement.
 */
@XmlEnum(String.class)
public enum UnitOfMeasure implements Serializable {
    @XmlEnumValue("kilograms")
    KILOGRAMS("kilograms"),
    @XmlEnumValue("centimeters")
    CENTIMETERS("centimeters"),
    @XmlEnumValue("square_meters")
    SQUARE_METERS("square_meters"),
    @XmlEnumValue("pcs")
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