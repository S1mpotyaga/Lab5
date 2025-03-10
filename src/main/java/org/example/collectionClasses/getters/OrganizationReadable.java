package org.example.collectionClasses.getters;

import org.example.collectionClasses.Organization;
import org.example.collectionClasses.enums.OrganizationType;

import java.util.Scanner;

public interface OrganizationReadable{

    static Organization readOrganization(){
        Scanner scanner = new Scanner(System.in);
        Organization result = new Organization();
        System.out.print("Enter name Organization: ");
        result.setName(scanner.nextLine());
        System.out.print("Enter full name: ");
        result.setFullName(scanner.nextLine());
        result.setOrganizationType(OrganizationType.readOrganizationType(scanner));
        scanner.close();
        return result;
    }
}