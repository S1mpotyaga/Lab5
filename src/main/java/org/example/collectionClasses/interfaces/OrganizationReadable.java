package org.example.collectionClasses.interfaces;

import org.example.collectionClasses.Organization;
import org.example.collectionClasses.enums.OrganizationType;

import java.util.Scanner;

public interface OrganizationReadable{

    static public Organization readOrganization(){
        Organization result = new Organization();
        result.setName(readName());
        result.setFullName(readFullName());
        result.setOrganizationType(OrganizationType.readOrganizationType());
        setIncorrectFields(result);
        return result;
    }

    static public void setIncorrectFields(Organization org){
        if (org.getName().isEmpty()){
            org.setName(readName());
        }
        if (org.getFullName().isEmpty() || org.getFullName().length() > 554){
            org.setFullName(readFullName());
        }
        if (org.getOrganizationType() == null){
            org.setOrganizationType(OrganizationType.readOrganizationType());
        }
    }

    private static String readFullName(){
        System.out.println("Enter full name organization: ");
        Scanner in = new Scanner(System.in);
        String result = in.next();
        in.close();
        return result;
    }

    private static String readName(){
        System.out.println("Enter name organization: ");
        Scanner in = new Scanner(System.in);
        String result = in.next();
        in.close();
        return result;
    }
}