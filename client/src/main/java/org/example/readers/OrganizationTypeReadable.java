package org.example.readers;

import org.example.collectionClasses.types.OrganizationType;
import org.example.queries.QueryType;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads the organization type.
 */
public interface OrganizationTypeReadable{

    /**
     * Gets the organization type.
     * @return an object of type OrganizationType.
     */
    public static OrganizationType readOrganizationType() throws NoSuchElementException{
        System.out.println("Enter Organization Type from the list:");
        OrganizationType.printAll();
        String tmp = Readable.scanner.nextLine();
        boolean flag = false;
        for (OrganizationType elem: OrganizationType.values())
            if (tmp.equals(elem.toString())){
                flag = true;
            }
        if (!flag){
            System.out.println("Wrong input OrganizationType. Enter please again one field from the list:");
            OrganizationType.printAll();
            return readOrganizationType();
        }
        return OrganizationType.fromString(tmp);
    }

    public static OrganizationType readOrganizationType(Scanner scanner) throws NoSuchElementException{
        String tmp = scanner.nextLine();
        boolean flag = false;
        for (OrganizationType elem: OrganizationType.values())
            if (tmp.equals(elem.toString())){
                flag = true;
            }
        if (!flag){
            throw new NoSuchElementException();
        }
        return OrganizationType.fromString(tmp);
    }
}
