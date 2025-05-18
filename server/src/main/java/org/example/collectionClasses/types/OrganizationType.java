package org.example.collectionClasses.types;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

import java.io.Serializable;

/**
 * Possible values of the Organization type.
 */
@XmlEnum(String.class)
public enum OrganizationType implements Serializable {
    @XmlEnumValue("government")
    GOVERNMENT("government"),
    @XmlEnumValue("trust")
    TRUST("trust"),
    @XmlEnumValue("open_joint_stock_company")
    OPEN_JOINT_STOCK_COMPANY("open_joint_stock_company");

    private String name;

    OrganizationType(String name) {
        this.name = name;
    }

    /**
     * method that maps a string to an OrganizationType object.
     * @param name
     * @return an OrganizationType object.
     */
    public static OrganizationType fromString(String name) {
        for (OrganizationType elem : OrganizationType.values()) {
            if (name.equalsIgnoreCase(elem.toString())) {
                return elem;
            }
        }
        return null;
    }

    static public void printAll(){
        for (OrganizationType cur: OrganizationType.values()){
            System.out.println(cur.toString());
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}