package org.example.collectionClasses.types;

import java.io.Serializable;

/**
 * Possible values of the Organization type.
 */
public enum OrganizationType implements Serializable {
    GOVERNMENT("government"),
    TRUST("trust"),
    OPEN_JOINT_STOCK_COMPANY("open_joint_stock_company");

    private String name;

    OrganizationType(String name) {
        this.name = name;
    }

    static public void printAll(){
        for (OrganizationType cur: OrganizationType.values()){
            System.out.println(cur.toString());
        }
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

    @Override
    public String toString() {
        return this.name;
    }
}