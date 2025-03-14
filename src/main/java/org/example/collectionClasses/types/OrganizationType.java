package org.example.collectionClasses.types;

import jakarta.xml.bind.annotation.*;

/**
 * Possible values of the Organization type.
 */
@XmlEnum(String.class)
public enum OrganizationType {
    @XmlEnumValue("government")
    GOVERNMENT("government"),
    @XmlEnumValue("trust")
    TRUST("trust"),
    @XmlEnumValue("open_joint_stock_company")
    OPEN_JOINT_STOCK_COMPANY("open_joint_stock_company");

    private String name;

    private OrganizationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
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
}