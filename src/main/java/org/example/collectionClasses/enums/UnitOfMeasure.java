package org.example.collectionClasses.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

@XmlEnum(String.class)
public enum UnitOfMeasure {
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

    public static UnitOfMeasure readUnitOfMeasure(Scanner scanner) {
        System.out.println("Please, enter only one field from the list:");
        for (UnitOfMeasure elem : UnitOfMeasure.values()) {
            System.out.println(elem.toString());
        }
        String tmp = scanner.next();
        return UnitOfMeasure.fromString(tmp);
    }

    public static UnitOfMeasure fromString(String name) {
        for (UnitOfMeasure elem : UnitOfMeasure.values()) {
            if (name.equalsIgnoreCase(elem.toString())) {
                return elem;
            }
        }
        return null;
    }
}