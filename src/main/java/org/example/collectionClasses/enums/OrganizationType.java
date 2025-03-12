package org.example.collectionClasses.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static OrganizationType readOrganizationType() {
        System.out.println("Please, enter only one field from the list:");
        Scanner in = new Scanner(System.in);
        for (OrganizationType elem : OrganizationType.values()) {
            System.out.println(elem.toString());
        }
        String tmp = in.next();
        return OrganizationType.fromString(tmp);
    }

    public static OrganizationType fromString(String name) {
        for (OrganizationType elem : OrganizationType.values()) {
            if (name.equalsIgnoreCase(elem.toString())) {
                return elem;
            }
        }
        return null;
    }
}