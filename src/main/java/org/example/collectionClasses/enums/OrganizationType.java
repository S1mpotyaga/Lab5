package org.example.collectionClasses.enums;

import java.util.ArrayList;

public enum OrganizationType {
    GOVERNMENT("government"),
    TRUST("trust"),
    OPEN_JOINT_STOCK_COMPANY("open_joint_stock_company");

    private String name;

    private OrganizationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public ArrayList<String> stringValues(){
        ArrayList<String> result = new ArrayList<>();
        for (OrganizationType elem: OrganizationType.values()){
            result.add(elem.toString());
        }
        return result;
    }

    public static OrganizationType fromString(String tmp) {
        for (OrganizationType cur : OrganizationType.values()) {
            if (cur.name.equalsIgnoreCase(tmp)) {
                return cur;
            }
        }
        return null;
    }
}
